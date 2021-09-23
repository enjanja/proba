package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ExaminationDTO;
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
		// TODO Auto-generated method stub
		return null;
	}

	public ExaminationDTO save(ExaminationDTO dto) {
		examinationRepository.save(mapper.toEntity(dto));
		return dto;
	}

	public void update(ExaminationDTO dto) {
		// TODO Auto-generated method stub
	}

	public void delete(ExaminationDTO dto) {
		examinationRepository.delete(mapper.toEntity(dto));
	}

}
