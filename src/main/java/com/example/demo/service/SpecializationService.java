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

	@Autowired
	SpecializationRepository specializationRepository;

	@Autowired
	SpecializationEntityDtoMapper specializationMaper;

	public SpecializationDTO save(SpecializationDTO specialization) {
		specializationRepository.save(specializationMaper.toEntity(specialization));
		return specialization;
	}
}
