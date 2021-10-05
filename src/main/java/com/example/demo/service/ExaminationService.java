package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ExaminationDTO;
import com.example.demo.dto.UpdateDTO;
import com.example.demo.entity.ExaminationEntity;
import com.example.demo.entity.ExaminationId;
import com.example.demo.mapper.ExaminationEntityDtoMapper;
import com.example.demo.repository.ExaminationRepository;

@Service
@Transactional
public class ExaminationService {

	ExaminationRepository examinationRepository;
	ExaminationEntityDtoMapper mapper;

	@Autowired
	public ExaminationService(ExaminationRepository examinationRepository, ExaminationEntityDtoMapper mapper) {
		this.examinationRepository = examinationRepository;
		this.mapper = mapper;
	}

	public Optional<ExaminationDTO> findById(ExaminationId id) {
		Optional<ExaminationEntity> entity = examinationRepository.findById(id);
		if (entity.isPresent()) {
			return Optional.of(mapper.toDto(entity.get()));
		}
		return Optional.empty();
	}

	public List<ExaminationDTO> getAll() {
		return examinationRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
	}

	public ExaminationDTO save(ExaminationDTO dto) {
		examinationRepository.save(mapper.toEntity(dto));
		return dto;
	}

	public ExaminationDTO updateDiagnosis(UpdateDTO dto) throws Exception {
		Optional<ExaminationEntity> existingExam = examinationRepository
				.findById(new ExaminationId(dto.getDoctorId(), dto.getPatientId(), dto.getDate()));
		if (existingExam.isEmpty()) {
			throw new Exception("There is no such appointment.");
		}

		ExaminationEntity exam = existingExam.get();
		exam.setDiagnosis(dto.getDiagnosis());
		examinationRepository.save(exam);

		return mapper.toDto(exam);
	}

	public void delete(ExaminationDTO dto) {
		examinationRepository.delete(mapper.toEntity(dto));
	}

//	public ExaminationDTO updateDate(UpdateDTO dto) throws Exception {
//		Optional<ExaminationEntity> existingExam = examinationRepository
//				.findById(new ExaminationId(dto.getDoctorId(), dto.getPatientId(), dto.getDate()));
//		if (existingExam.isEmpty()) {
//			throw new Exception("There is no such appointment.");
//		}
//
//		Optional<ExaminationEntity> takenExam = examinationRepository
//				.findById(new ExaminationId(dto.getDoctorId(), dto.getPatientId(), dto.getNewDate()));
//		if (takenExam.isPresent()) {
//			throw new Exception("Appointment with the new date already exists.");
//		}
//		ExaminationEntity exam = existingExam.get();
//		exam.setId(new ExaminationId(dto.getDoctorId(), dto.getPatientId(), dto.getNewDate()));
//		examinationRepository.save(exam);
//
//		return mapper.toDto(exam);
//	}

}
