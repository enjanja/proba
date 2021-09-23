package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.ExaminationDTO;
import com.example.demo.entity.ExaminationEntity;

@Mapper(componentModel = "spring")
public interface ExaminationEntityDtoMapper {

	public ExaminationDTO toDto(ExaminationEntity entity);

	public ExaminationEntity toEntity(ExaminationDTO dto);
//	@Autowired
//	DoctorEntityDtoMapper doctor;
//
//	@Autowired
//	PatientEntityDtoMapper patient;
//
//	public ExaminationDTO toDto(ExaminationEntity examination) {
//		ExaminationDTO dto = new ExaminationDTO();
//		dto.setId(examination.getId());
////		dto.setDoctor(doctor.toDto(examination.getDoctor()));
////		dto.setPatient(patient.toDto(examination.getPatient()));
//		dto.setDate(examination.getDate());
//		dto.setDiagnosis(examination.getDiagnosis());
//		return dto;
//	}
//
//	public ExaminationEntity toEntity(ExaminationDTO examination) {
//		ExaminationEntity entity = new ExaminationEntity();
//		entity.setId(examination.getId());
////		entity.setDoctor(doctor.toEntity(examination.getDoctor()));
////		entity.setPatient(patient.toEntity(examination.getPatient()));
//		entity.setDate(examination.getDate());
//		entity.setDiagnosis(examination.getDiagnosis());
//		return entity;
//	}

}
