package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DoctorDTO;
import com.example.demo.entity.DoctorEntity;
import com.example.demo.mapper.DoctorEntityDtoMapper;
import com.example.demo.repository.DoctorRepository;

@Service
@Transactional
public class DoctorService {
	DoctorRepository doctorRepository;
	DoctorEntityDtoMapper doctorEntityDtoMapper;

	@Autowired
	public DoctorService(DoctorRepository doctorRepository, DoctorEntityDtoMapper doctorEntityDtoMapper) {
		super();
		this.doctorRepository = doctorRepository;
		this.doctorEntityDtoMapper = doctorEntityDtoMapper;
	}

	public Optional<DoctorDTO> findById(int id) {
		Optional<DoctorEntity> entity = doctorRepository.findById(id);
		if (entity.isPresent()) {
			return Optional.of(doctorEntityDtoMapper.toDto(entity.get()));
		}
		return Optional.empty();
	}

	public List<DoctorDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public DoctorDTO save(DoctorDTO dto) {
		doctorRepository.save(doctorEntityDtoMapper.toEntity(dto));
		return dto;
	}

	public void update(DoctorDTO dto) {
		// TODO Auto-generated method stub
	}

	public void delete(DoctorDTO dto) {
		doctorRepository.delete(doctorEntityDtoMapper.toEntity(dto));
	}

}
