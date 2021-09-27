package com.example.demo.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import com.example.demo.dto.PatientDTO;
import com.example.demo.entity.PatientEntity;

@Mapper(componentModel = "spring", uses = { DoctorEntitySimpleDtoMapper.class,
		PatientEntitySimpleDtoMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DoctorDtoMapper {
	PatientDTO toDto(PatientEntity entity);

	PatientEntity toEntity(PatientDTO dto);
}
