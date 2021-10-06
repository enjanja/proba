package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PatientDTO;
import com.example.demo.entity.PatientEntity;
import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
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

	public PatientDTO findById(Long id) {
		Optional<PatientEntity> entity = patientRepository.findById(id);
		if (entity.isEmpty()) {
			throw new ResourceNotFoundException("Patient doesn't exist.");
		}
		return patientEntityDtoMapper.toDto(entity.get());
	}

	public List<PatientDTO> getAll() {
		List<PatientEntity> entities = patientRepository.findAll();
		return entities.stream().map(entity -> {
			return patientEntityDtoMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

	public PatientDTO save(PatientDTO dto) {
		Optional<PatientEntity> entity = patientRepository.findByJmbg(dto.getJmbg());
		if (entity.isPresent()) {
			throw new ResourceAlreadyExistsException(dto.getJmbg(), "Patient with this jmbg already exists");
		}
		PatientEntity nurse = patientRepository.save(patientEntityDtoMapper.toEntity(dto));
		return patientEntityDtoMapper.toDto(nurse);
	}

	public PatientDTO update(PatientDTO dto) {
		Optional<PatientEntity> entity = patientRepository.findById(dto.getId());
		if (entity.isEmpty()) {
			throw new ResourceNotFoundException("Patient doesn't exist!");
		}
		entity.get().setName(dto.getName());

		PatientEntity patient = patientRepository.save(entity.get());
		return patientEntityDtoMapper.toDto(patient);

	}

	public PatientDTO delete(Long id) {
		Optional<PatientEntity> patientEntity = patientRepository.findById(id);
		if (patientEntity.isEmpty()) {
			throw new ResourceNotFoundException("Patient with id " + id + " does not exist!");
		}

		patientRepository.delete(patientEntity.get());

		return patientEntityDtoMapper.toDto(patientEntity.get());

	}

}
