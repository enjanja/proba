package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PatientDTO;
import com.example.demo.entity.PatientEntity;
import com.example.demo.mapper.PatientEntityDtoMapper;
import com.example.demo.repository.PatientRepository;
import com.example.demo.service.PatientService;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

	PatientRepository patientRepository;
	PatientEntityDtoMapper patientEntityDtoMapper;

	@Autowired
	public PatientServiceImpl(PatientRepository patientRepository, PatientEntityDtoMapper patientEntityDtoMapper) {
		super();
		this.patientRepository = patientRepository;
		this.patientEntityDtoMapper = patientEntityDtoMapper;
	}

	// not good
	@Override
	public Optional<PatientDTO> findById(int id) {
		Optional<PatientEntity> patient = patientRepository.findById(id);
		if (patient.isPresent()) {
			return Optional.of(patientEntityDtoMapper.toDto(patient.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<PatientDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientDTO save(PatientDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PatientDTO> update(PatientDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientDTO delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
