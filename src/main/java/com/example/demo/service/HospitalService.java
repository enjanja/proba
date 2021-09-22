package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.HospitalDTO;
import com.example.demo.entity.HospitalEntity;
import com.example.demo.mapper.DoctorEntityDtoMapper;
import com.example.demo.mapper.HospitalEntityDtoMapper;
import com.example.demo.mapper.NurseEntityDtoMapper;
import com.example.demo.repository.HospitalRepozitory;

@Service
@Transactional
public class HospitalService {
	HospitalRepozitory hospitalRepozitory;
	HospitalEntityDtoMapper hospitalEntityDtoMapper;
	DoctorEntityDtoMapper doctorEntityDtoMapper;
	NurseEntityDtoMapper nurseEntityDtoMapper;

	@Autowired
	public HospitalService(HospitalRepozitory hospitalRepozitory, HospitalEntityDtoMapper hospitalEntityDtoMapper,
			DoctorEntityDtoMapper doctorEntityDtoMapper, NurseEntityDtoMapper nurseEntityDtoMapper) {
		super();
		this.hospitalRepozitory = hospitalRepozitory;
		this.hospitalEntityDtoMapper = hospitalEntityDtoMapper;
		this.doctorEntityDtoMapper = doctorEntityDtoMapper;
		this.nurseEntityDtoMapper = nurseEntityDtoMapper;
	}

	public Optional<HospitalDTO> findById(int id) {
		Optional<HospitalEntity> hospital = hospitalRepozitory.findById(id);
		if (hospital.isPresent()) {
			return Optional.of(hospitalEntityDtoMapper.toDto(hospital.get()));
		}
		return Optional.empty();
	}

	public List<HospitalDTO> getAll() {
		List<HospitalEntity> entities = hospitalRepozitory.findAll();
		return entities.stream().map(entity -> {
			return hospitalEntityDtoMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

	public void save(HospitalDTO dto) {
		hospitalRepozitory.save(hospitalEntityDtoMapper.toEntity(dto));
	}

	public Optional<HospitalDTO> update(HospitalDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	public HospitalDTO delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
