package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.SimpleSpecializationDTO;
import com.example.demo.entity.SpecializationEntity;

@Mapper(componentModel = "spring")
public interface SimpleSpecializationEntityDtoMapper {

	public SimpleSpecializationDTO toDto(SpecializationEntity entity);

	public SpecializationEntity toEntity(SimpleSpecializationDTO dto);

}
