package com.example.demo.mapper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.DoctorDTO;
import com.example.demo.dto.ExaminationDTO;
import com.example.demo.dto.HospitalDTO;
import com.example.demo.entity.DoctorEntity;
import com.example.demo.entity.ExaminationEntity;
import com.example.demo.entity.HospitalEntity;
import com.example.demo.repository.HospitalRepozitory;

@Component
public class MyDoctorEntityDtoMapper {
	DoctorEntitySimpleDtoMapper simpleDoctorMapper;
	PatientEntitySimpleDtoMapper simplePatientMapper;

	@Autowired
	private HospitalRepozitory hospitalRepozitory;

	@Autowired
	private HospitalEntityDtoMapper hospitalMapper;

//	@Autowired
//	private ExaminationEntityDtoMapper examinationMapper;
//
//	@Autowired
//	private ExaminationRepository examinationRepository;

	@Autowired
	public MyDoctorEntityDtoMapper(DoctorEntitySimpleDtoMapper simpleDoctorMapper,
			PatientEntitySimpleDtoMapper simplePatientMapper) {
		this.simpleDoctorMapper = simpleDoctorMapper;
		this.simplePatientMapper = simplePatientMapper;
	}

	public DoctorDTO toDto(DoctorEntity entity) {
		DoctorDTO dto = new DoctorDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setPassword(entity.getPassword());
		dto.setUsername(entity.getUsername());
		for (ExaminationEntity examination : entity.getExaminations()) {
			dto.getExaminations()
					.add(new ExaminationDTO(simpleDoctorMapper.toDto(entity),
							simplePatientMapper.toDto(examination.getPatient()), examination.getId().getDate(),
							examination.getDiagnosis()));
		}

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

		for (ExaminationDTO examinationDto : dto.getExaminations()) {
			entity.getExaminations()
					.add(new ExaminationEntity(entity, simplePatientMapper.toEntity(examinationDto.getPatient()),
							examinationDto.getId().getDate(), examinationDto.getDiagnosis()));
		}

		return entity;
	};

}
