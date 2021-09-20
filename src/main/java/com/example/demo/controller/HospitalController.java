package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DoctorDTO;
import com.example.demo.dto.ExaminationDTO;
import com.example.demo.dto.HospitalDTO;
import com.example.demo.dto.SpecializationDTO;
import com.example.demo.mapper.DoctorEntityDtoMapper;
import com.example.demo.mapper.ExaminationEntityDtoMapper;
import com.example.demo.mapper.HospitalEntityDtoMapper;
import com.example.demo.mapper.NurseEntityDtoMapper;
import com.example.demo.mapper.PatientEntityDtoMapper;
import com.example.demo.mapper.SpecializationEntityDtoMapper;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.ExaminationRepository;
import com.example.demo.repository.HospitalRepozitory;
import com.example.demo.repository.NurseRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.SpecializationRepository;

@RestController
public class HospitalController {

	@Autowired
	HospitalRepozitory hospitalRepozitory;

	@Autowired
	HospitalEntityDtoMapper mapper;

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	DoctorEntityDtoMapper doctorMapper;

	@Autowired
	SpecializationRepository specializationRepository;

	@Autowired
	SpecializationEntityDtoMapper specializationEntityDtoMapper;

	@Autowired
	NurseRepository nurseRepository;

	@Autowired
	NurseEntityDtoMapper nurseEntityDtoMapper;

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	PatientEntityDtoMapper patientEntityDtoMapper;

	@Autowired
	ExaminationRepository examinationRepository;

	@Autowired
	ExaminationEntityDtoMapper examinationEntityDtoMapper;

	@PostMapping
	public String save(@RequestBody HospitalDTO dto) {

		hospitalRepozitory.save(mapper.toEntity(dto));
		return "successful";
	}

	@PostMapping("doctor")
	public String saveDoctor(@RequestBody DoctorDTO dto) {

		doctorRepository.save(doctorMapper.toEntity(dto));
		return "successful";
	}

	@PostMapping("specialization")
	public String saveSpecialization(@RequestBody SpecializationDTO dto) {

		specializationRepository.save(specializationEntityDtoMapper.toEntity(dto));
		return "successful";
	}

//	@PostMapping("nurse")
//	public String saveNurse(@RequestBody NurseDTO dto) {
//
//		nurseRepository.save(nurseEntityDtoMapper.toEntity(dto));
//		return "successful";
//	}
//
//	@PostMapping("patient")
//	public String savePatient(@RequestBody PatientDTO dto) {
//
//		patientRepository.save(patientEntityDtoMapper.toEntity(dto));
//		return "successful";
//	}

	@PostMapping("examination")
	public String saveExamination(@RequestBody ExaminationDTO dto) {

		examinationRepository.save(examinationEntityDtoMapper.toEntity(dto));
		return "successful";
	}
}
