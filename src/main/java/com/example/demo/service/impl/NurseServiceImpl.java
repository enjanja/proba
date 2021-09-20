package com.example.demo.service.impl;

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
import com.example.demo.service.NurseService;

@Service
@Transactional
public class NurseServiceImpl implements NurseService {

	NurseRepository nurseRepository;
	NurseEntityDtoMapper nurseEntityDtoMapper;

	@Autowired
	public NurseServiceImpl(NurseRepository nurseRepository, NurseEntityDtoMapper nurseEntityDtoMapper) {
		super();
		this.nurseRepository = nurseRepository;
		this.nurseEntityDtoMapper = nurseEntityDtoMapper;
	}

	@Override
	public Optional<NurseDTO> findById(int id) {
		Optional<NurseEntity> nurse = nurseRepository.findById(id);
		if (nurse.isPresent()) {
			return Optional.of(nurseEntityDtoMapper.toDto(nurse.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<NurseDTO> getAll() {
		List<NurseEntity> entities = nurseRepository.findAll();
		return entities.stream().map(entity -> {
			return nurseEntityDtoMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	public NurseDTO save(NurseDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<NurseDTO> update(NurseDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NurseDTO delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
