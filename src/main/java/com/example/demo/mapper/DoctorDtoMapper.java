package com.example.demo.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import com.example.demo.dto.DoctorDTO;
import com.example.demo.entity.DoctorEntity;

@Mapper(componentModel = "spring", uses = { DoctorEntitySimpleDtoMapper.class,
		PatientEntitySimpleDtoMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DoctorDtoMapper {
	DoctorDTO toDto(DoctorEntity entity);

	DoctorEntity toEntity(DoctorDTO dto);
}
