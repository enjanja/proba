package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.DoctorDTO;
import com.example.demo.entity.DoctorEntity;

@Mapper(componentModel = "spring")
public interface DoctorEntityDtoMapper {

	public DoctorDTO toDto(DoctorEntity doctor);

	public DoctorEntity toEntity(DoctorDTO doctor);
}
