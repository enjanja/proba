package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DoctorDTO;
import com.example.demo.dto.HospitalDTO;
import com.example.demo.entity.DoctorEntity;
import com.example.demo.entity.ExaminationEntity;
import com.example.demo.entity.HospitalEntity;
import com.example.demo.entity.PatientEntity;
import com.example.demo.mapper.DoctorEntityDtoMapper;
import com.example.demo.mapper.ExaminationEntityDtoMapper;
import com.example.demo.mapper.HospitalEntityDtoMapper;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.HospitalRepozitory;
import com.example.demo.repository.PatientRepository;

@Service
@Transactional
public class DoctorService {
	DoctorRepository doctorRepository;
	DoctorEntityDtoMapper doctorMapper;
	ExaminationEntityDtoMapper examinationMapper;
	PatientRepository patientRepository;

	HospitalRepozitory hospitalRepozitory;
	HospitalEntityDtoMapper hospitalMapper;

	@Autowired
	public DoctorService(DoctorRepository doctorRepository, DoctorEntityDtoMapper doctorEntityDtoMapper,
			HospitalRepozitory hospitalRepozitory, HospitalEntityDtoMapper hospitalMapper,
			ExaminationEntityDtoMapper examinationMapper, PatientRepository patientRepository) {
		super();
		this.doctorRepository = doctorRepository;
		this.hospitalRepozitory = hospitalRepozitory;
		this.doctorMapper = doctorEntityDtoMapper;
		this.hospitalMapper = hospitalMapper;
		this.examinationMapper = examinationMapper;
		this.patientRepository = patientRepository;
	}

	public Optional<DoctorDTO> findById(Long id) {
		Optional<DoctorEntity> entity = doctorRepository.findById(id);
		if (entity.isPresent()) {
			return Optional.of(doctorMapper.toDto(entity.get()));
		}
		return Optional.empty();
	}

	public List<DoctorDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public DoctorDTO save(DoctorDTO dto) throws Exception {
		Optional<DoctorEntity> doctorEntity = doctorRepository.findByUsername(dto.getUsername());
		if (doctorEntity.isPresent()) {
			throw new Exception("Doctor already exists.");
		}
		DoctorEntity doctor = doctorRepository.save(doctorMapper.toEntity(dto));
		return doctorMapper.toDto(doctor);
	}

	public void update(DoctorDTO dto) throws Exception {
		Optional<DoctorEntity> exisitngDoctor = doctorRepository.findById(dto.getId());
		if (exisitngDoctor.isEmpty()) {
			throw new Exception("Doctor doesn't exist!");
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

//		doctor.setExaminations(
//				dto.getExaminations().stream().map(examinationMapper::toEntity).collect(Collectors.toSet()));

		doctorRepository.save(doctor);

	}

	public void delete(DoctorDTO dto) throws Exception {
		Optional<DoctorEntity> doctorEntity = doctorRepository.findById(dto.getId());
		if (doctorEntity.isEmpty()) {
			throw new Exception("Doctor doesnt exist.");
		}

		doctorRepository.delete(doctorMapper.toEntity(dto));

//		for (HospitalEntity hospital : doctorEntity.get().getHospitals()) {
//			List<DoctorEntity> doctors = doctorRepository.findByHospitals(hospital);
//			if (doctors.isEmpty()) {
//				hospitalRepozitory.delete(hospital);
//			}
//		}
	}

	public DoctorDTO addExam(Long patientId, Long doctorId, LocalDate date) throws Exception {
		Optional<DoctorEntity> doctorEntity = doctorRepository.findById(doctorId);
		if (doctorEntity.isEmpty()) {
			throw new Exception("Doctor doesnt exist");
		}

		Optional<PatientEntity> patientEntity = patientRepository.findById(patientId);
		if (patientEntity.isEmpty()) {
			throw new Exception("PAtient doesnt exist");
		}

		DoctorEntity doctor = doctorEntity.get();
		PatientEntity patient = patientEntity.get();
		doctor.getExaminations().add(new ExaminationEntity(doctor, patient, date, ""));
		doctor = doctorRepository.save(doctor);
		return doctorMapper.toDto(doctor);

	}

	public DoctorDTO removeExam(Long patientId, Long doctorId, LocalDate date) throws Exception {
		Optional<DoctorEntity> doctorEntity = doctorRepository.findById(doctorId);
		if (doctorEntity.isEmpty()) {
			throw new Exception("Doctor doesnt exist");
		}

		Optional<PatientEntity> patientEntity = patientRepository.findById(patientId);
		if (patientEntity.isEmpty()) {
			throw new Exception("PAtient doesnt exist");
		}

		DoctorEntity doctor = doctorEntity.get();
		PatientEntity patient = patientEntity.get();
		doctor.getExaminations().remove(new ExaminationEntity(doctor, patient, date, ""));
		doctor = doctorRepository.save(doctor);

		return doctorMapper.toDto(doctor);
	}

}
