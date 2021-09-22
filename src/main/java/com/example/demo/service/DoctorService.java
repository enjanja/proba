package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DoctorDTO;
import com.example.demo.dto.HospitalDTO;
import com.example.demo.entity.DoctorEntity;
import com.example.demo.entity.HospitalEntity;
import com.example.demo.mapper.DoctorEntityDtoMapper;
import com.example.demo.mapper.HospitalEntityDtoMapper;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.HospitalRepozitory;

@Service
@Transactional
public class DoctorService {
	DoctorRepository doctorRepository;
	HospitalRepozitory hospitalRepozitory;
	DoctorEntityDtoMapper doctorEntityDtoMapper;
	HospitalEntityDtoMapper hospitalMapper;

	@Autowired
	public DoctorService(DoctorRepository doctorRepository, DoctorEntityDtoMapper doctorEntityDtoMapper,
			HospitalRepozitory hospitalRepozitory, HospitalEntityDtoMapper hospitalMapper) {
		super();
		this.doctorRepository = doctorRepository;
		this.hospitalRepozitory = hospitalRepozitory;
		this.doctorEntityDtoMapper = doctorEntityDtoMapper;
		this.hospitalMapper = hospitalMapper;
	}

	public Optional<DoctorDTO> findById(Long id) {
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

	public void update(DoctorDTO dto) throws Exception {
		Optional<DoctorEntity> exisitngDoctor = doctorRepository.findById(dto.getId());
		if (exisitngDoctor.isEmpty()) {
			throw new Exception();
		}

		DoctorEntity doctor = exisitngDoctor.get();
		doctor.setName(dto.getName());
		doctor.setUsername(dto.getUsername());
		doctor.setPassword(dto.getPassword());

		doctor.getHospitals().clear();
		for (HospitalDTO hospital : dto.getHospitals()) {
			HospitalEntity hospitalEntity = hospitalMapper.toEntity(hospital);
			doctor.addHospital(hospitalEntity);
		}

		doctorRepository.save(doctor);

	}

	public void delete(DoctorDTO dto) throws Exception {
		Optional<DoctorEntity> doctorEntity = doctorRepository.findById(dto.getId());
		if (doctorEntity.isEmpty()) {
			throw new Exception("Doctor doesnt exist.");
		}

		doctorRepository.delete(doctorEntityDtoMapper.toEntity(dto));

//		for (HospitalEntity hospital : doctorEntity.get().getHospitals()) {
//			List<DoctorEntity> doctors = doctorRepository.findByHospitals(hospital);
//			if (doctors.isEmpty()) {
//				hospitalRepozitory.delete(hospital);
//			}
//		}
	}

}
