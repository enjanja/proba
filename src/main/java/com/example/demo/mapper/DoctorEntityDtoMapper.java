package com.example.demo.mapper;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.DoctorDTO;
import com.example.demo.dto.HospitalDTO;
import com.example.demo.entity.DoctorEntity;
import com.example.demo.entity.HospitalEntity;
import com.example.demo.repository.HospitalRepozitory;

@Component
@Transactional
public class DoctorEntityDtoMapper {

	@Autowired
	private HospitalRepozitory hospitalRepozitory;

	@Autowired
	private HospitalEntityDtoMapper hospitalMapper;

	public DoctorDTO toDto(DoctorEntity entity) {
		DoctorDTO dto = new DoctorDTO();

		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setPassword(entity.getPassword());
		dto.setUsername(entity.getUsername());
		Set<HospitalDTO> hospitals = entity.getHospitals().stream().map(hospital -> hospitalMapper.toDto(hospital))
				.collect(Collectors.toSet());
		dto.setHospitals(hospitals);
		return dto;
	}

	public DoctorEntity toEntity(DoctorDTO dto) {
		DoctorEntity entity = new DoctorEntity();

		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setPassword(dto.getPassword());
		entity.setUsername(dto.getUsername());

		for (HospitalDTO hospitalDto : dto.getHospitals()) {
			Optional<HospitalEntity> existingHospital = hospitalRepozitory.findById(hospitalDto.getId());
			if (existingHospital.isPresent()) {
				entity.addHospital(existingHospital.get());
			} else {
				HospitalEntity hospital = new HospitalEntity();
				hospital = hospitalMapper.toEntity(hospitalDto);
				entity.addHospital(hospital);
			}
		}

		return entity;
	};

}
