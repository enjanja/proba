package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.PatientSimpleDTO;
import com.example.demo.entity.PatientEntity;

@Mapper(componentModel = "spring")
public interface PatientSimpleEntityDtoMapper {
	PatientEntity toEntity(PatientSimpleDTO dto);

	PatientSimpleDTO toDto(PatientEntity dto);
}
