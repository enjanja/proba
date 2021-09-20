package com.example.demo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.DoctorDTO;
import com.example.demo.dto.HospitalDTO;
import com.example.demo.entity.DoctorEntity;
import com.example.demo.entity.HospitalEntity;

@Component
public class HospitalEntityDtoMapper {

	@Autowired
	DoctorEntityDtoMapper doctorDtoMapper;

	public HospitalDTO toDto(HospitalEntity entity) {

		HospitalDTO dto = new HospitalDTO();

		dto.setAddress(entity.getAddress());
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		for (DoctorEntity doctor : entity.getDoctors()) {
			dto.getDoctors().add(doctorDtoMapper.toDto(doctor));
		}

		return dto;
	}

	public HospitalEntity toEntity(HospitalDTO dto) {
		HospitalEntity entity = new HospitalEntity();

		entity.setAddress(dto.getAddress());
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		for (DoctorDTO doctor : dto.getDoctors()) {
			entity.getDoctors().add(doctorDtoMapper.toEntity(doctor));
		}
		return entity;
	}

}
