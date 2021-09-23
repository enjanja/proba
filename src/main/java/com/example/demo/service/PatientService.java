package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PatientDTO;
import com.example.demo.entity.PatientEntity;
import com.example.demo.mapper.PatientEntityDtoMapper;
import com.example.demo.repository.PatientRepository;

@Service
@Transactional
public class PatientService {

	PatientRepository patientRepository;
	PatientEntityDtoMapper patientEntityDtoMapper;

	@Autowired
	public PatientService(PatientRepository patientRepository, PatientEntityDtoMapper patientEntityDtoMapper) {
		super();
		this.patientRepository = patientRepository;
		this.patientEntityDtoMapper = patientEntityDtoMapper;
	}

	public Optional<PatientDTO> findById(Long id) {
		Optional<PatientEntity> entity = patientRepository.findById(id);
		if (entity.isPresent()) {
			return Optional.of(patientEntityDtoMapper.toDto(entity.get()));
		}
		return Optional.empty();
	}

	public List<PatientDTO> getAll() {
		List<PatientEntity> entities = patientRepository.findAll();
		return entities.stream().map(entity -> {
			return patientEntityDtoMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

	public PatientDTO save(PatientDTO dto) throws Exception {
		Optional<PatientEntity> entity = patientRepository.findById(dto.getId());
		if (entity.isPresent()) {
			throw new Exception("Patient already exists");
		}
		PatientEntity nurse = patientRepository.save(patientEntityDtoMapper.toEntity(dto));
		return patientEntityDtoMapper.toDto(nurse);
	}

	// nope

	public Optional<PatientDTO> update(PatientDTO dto) {
		Optional<PatientEntity> entity = patientRepository.findById(dto.getId());
		if (entity.isPresent()) {
			PatientEntity patient = patientRepository.save(patientEntityDtoMapper.toEntity(dto));
			return Optional.of(patientEntityDtoMapper.toDto(patient));
		}
		return Optional.empty();
	}

	public PatientDTO delete(Long id) throws Exception {
		Optional<PatientEntity> studentEntity = patientRepository.findById(id);
		if (studentEntity.isPresent()) {
			patientRepository.delete(studentEntity.get());
			return patientEntityDtoMapper.toDto(studentEntity.get());
		}
		throw new Exception("Patient with id " + id + " does not exist!");
	}

}
