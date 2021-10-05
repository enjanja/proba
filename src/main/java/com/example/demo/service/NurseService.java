package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.NurseDTO;
import com.example.demo.entity.NurseEntity;
import com.example.demo.mapper.NurseEntityDtoMapper;
import com.example.demo.repository.NurseRepository;

@Service
@Transactional
public class NurseService {

	NurseRepository nurseRepository;
	NurseEntityDtoMapper nurseEntityDtoMapper;

	@Autowired
	public NurseService(NurseRepository nurseRepository, NurseEntityDtoMapper nurseEntityDtoMapper) {
		super();
		this.nurseRepository = nurseRepository;
		this.nurseEntityDtoMapper = nurseEntityDtoMapper;
	}

	public Optional<NurseDTO> findById(Long id) {
		Optional<NurseEntity> nurse = nurseRepository.findById(id);
		if (nurse.isPresent()) {
			return Optional.of(nurseEntityDtoMapper.toDto(nurse.get()));
		}
		return Optional.empty();
	}

	public List<NurseDTO> getAll() {
		List<NurseEntity> entities = nurseRepository.findAll();
		return entities.stream().map(entity -> {
			return nurseEntityDtoMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

	public NurseDTO save(NurseDTO dto) throws Exception {
		Optional<NurseEntity> entity = nurseRepository.findById(dto.getId());
		if (entity.isPresent()) {
			throw new Exception("Nurse already exists");
		}
		NurseEntity nurse = nurseRepository.save(nurseEntityDtoMapper.toEntity(dto));
		return nurseEntityDtoMapper.toDto(nurse);
	}

	public Optional<NurseDTO> update(NurseDTO dto) {
		Optional<NurseEntity> entity = nurseRepository.findById(dto.getId());
		if (entity.isPresent()) {

		}
		return Optional.empty();
	}

	public NurseDTO delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
