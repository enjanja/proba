package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.ExaminationDTO;
import com.example.demo.entity.ExaminationEntity;

@Mapper(componentModel = "spring")
//@Component
//@Transactional
public interface ExaminationEntityDtoMapper {

	public ExaminationDTO toDto(ExaminationEntity entity);

	public ExaminationEntity toEntity(ExaminationDTO dto);

//	@Autowired
//	DoctorEntityDtoMapper doctorMapper;
//	@Autowired
//	DoctorRepository doctorRepository;
//
//	@Autowired
//	PatientEntityDtoMapper patientMapper;
//	@Autowired
//	PatientRepository patientRepository;
//
//	@Autowired
//	private ExaminationRepository examinationRepository;
//
//	public ExaminationDTO toDto(ExaminationEntity examination) {
//		ExaminationDTO dto = new ExaminationDTO();
//		dto.setId(examination.getId());
//		dto.setDoctor(doctorMapper.toDto(examination.getDoctor())); // i ovo sranje pravi problem
//		dto.setPatient(patientMapper.toDto(examination.getPatient()));
////		dto.setDate(examination.getDate());
//		dto.setDiagnosis(examination.getDiagnosis());
//		return dto;
//	}
//
//	public ExaminationEntity toEntity(ExaminationDTO examination) {
//		ExaminationEntity entity = new ExaminationEntity();
//		entity.setId(examination.getId());
//		entity.setDoctor(doctorMapper.toEntity(examination.getDoctor()));
//		entity.setPatient(patientMapper.toEntity(examination.getPatient()));
////		entity.setDate(examination.getDate());
//		entity.setDiagnosis(examination.getDiagnosis());

	// dodavanje pregleda u doktora
//		Optional<DoctorEntity> existingDoctor = doctorRepository.findById(examination.getDoctor().getId());
//		DoctorEntity drEntity = existingDoctor.get();
////		Optional<ExaminationEntity> existingExamination = examinationRepository.findById(entity.getId());
//		if (drEntity.getExaminations().contains(entity)) {
//			drEntity.removeExamination(entity);
//			drEntity.addExamination(entity);
//		} else {
//			drEntity.addExamination(entity);
//		}
//
////		drEntity.addExamination(entity);
//		doctorRepository.save(drEntity);

//	return entity;
//}

}
