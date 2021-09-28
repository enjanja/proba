package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SpecializationDTO;
import com.example.demo.mapper.SpecializationEntityDtoMapper;
import com.example.demo.repository.SpecializationRepository;

@Service
@Transactional
public class SpecializationService {
	SpecializationRepository specializationRepository;
	SpecializationEntityDtoMapper specializationDtoMapper;

	@Autowired
	public SpecializationService(SpecializationRepository specializationRepository,
			SpecializationEntityDtoMapper specializationDtoMapper) {
		super();
		this.specializationRepository = specializationRepository;
		this.specializationDtoMapper = specializationDtoMapper;
	}

	public SpecializationDTO save(SpecializationDTO dto) {
		specializationRepository.save(specializationDtoMapper.toEntity(dto));
		return dto;
	}

}
