package com.example.demo.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { DoctorEntitySimpleDtoMapper.class,
		PatientEntitySimpleDtoMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR)

public interface PatientDtoMapper {

}
