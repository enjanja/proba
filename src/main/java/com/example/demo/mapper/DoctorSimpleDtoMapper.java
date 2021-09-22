package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.DoctorSimpleDTO;
import com.example.demo.entity.DoctorEntity;

@Mapper(componentModel = "spring")
public interface DoctorSimpleDtoMapper {
	public DoctorSimpleDTO toDto(DoctorEntity doctor);

	public DoctorEntity toEntity(DoctorSimpleDTO doctor);
}
