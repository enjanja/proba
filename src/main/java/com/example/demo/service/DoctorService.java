package com.example.demo.service;

import java.time.LocalDateTime;
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
import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
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

	public DoctorDTO findById(Long id) {
		Optional<DoctorEntity> entity = doctorRepository.findById(id);
		if (entity.isEmpty()) {
			throw new ResourceNotFoundException("Doctor doesn't exist");
		}
		return doctorMapper.toDto(entity.get());
	}

	public List<DoctorDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public DoctorDTO save(DoctorDTO dto) {
		Optional<DoctorEntity> doctorEntity = doctorRepository.findByUsername(dto.getUsername());
		if (doctorEntity.isPresent()) {
			throw new ResourceAlreadyExistsException(dto.getUsername(), "Doctor with this username already exists.");
		}
		DoctorEntity doctor = doctorRepository.save(doctorMapper.toEntity(dto));
		return doctorMapper.toDto(doctor);
	}

	public void update(DoctorDTO dto) {
		Optional<DoctorEntity> exisitngDoctor = doctorRepository.findById(dto.getId());
		if (exisitngDoctor.isEmpty()) {
			throw new ResourceNotFoundException("This doctor does't exist");
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

	public void delete(Long id) {
		Optional<DoctorEntity> doctorEntity = doctorRepository.findById(id);
		if (doctorEntity.isEmpty()) {
			throw new ResourceNotFoundException("Doctor doesn't exist.");
		}

		doctorRepository.deleteById(id);
	}

<<<<<<< HEAD
	public DoctorDTO addExam(Long patientId, Long doctorId, LocalDateTime date) throws Exception {
=======
	public DoctorDTO addExam(Long patientId, Long doctorId, LocalDate date) {
>>>>>>> 9732208e6649a2e185e41928866e2e9df1034891
		Optional<DoctorEntity> doctorEntity = doctorRepository.findById(doctorId);
		if (doctorEntity.isEmpty()) {
			throw new ResourceNotFoundException("Doctor doesn't exist.");
		}

		Optional<PatientEntity> patientEntity = patientRepository.findById(patientId);
		if (patientEntity.isEmpty()) {
			throw new ResourceNotFoundException("Patient doesn't exist.");
		}

		DoctorEntity doctor = doctorEntity.get();
		PatientEntity patient = patientEntity.get();

		if (doctor.getExaminations().contains(new ExaminationEntity(doctor, patient, date, ""))) {
			throw new ResourceAlreadyExistsException(null, "This examination already exists.");
		}
		doctor.getExaminations().add(new ExaminationEntity(doctor, patient, date, ""));
		doctor = doctorRepository.save(doctor);
		return doctorMapper.toDto(doctor);

	}

<<<<<<< HEAD
	public DoctorDTO removeExam(Long patientId, Long doctorId, LocalDateTime dateTime) throws Exception {
=======
	public DoctorDTO removeExam(Long patientId, Long doctorId, LocalDate date) {
>>>>>>> 9732208e6649a2e185e41928866e2e9df1034891
		Optional<DoctorEntity> doctorEntity = doctorRepository.findById(doctorId);
		if (doctorEntity.isEmpty()) {
			throw new ResourceNotFoundException("Doctor doesn't exist.");
		}

		Optional<PatientEntity> patientEntity = patientRepository.findById(patientId);
		if (patientEntity.isEmpty()) {
			throw new ResourceNotFoundException("Patient doesn't exist.");
		}

		DoctorEntity doctor = doctorEntity.get();
		PatientEntity patient = patientEntity.get();
		doctor.removeExamination(patient, dateTime);

//		patientRepository.save(patient);
		doctor = doctorRepository.save(doctor);

		return doctorMapper.toDto(doctor);
	}

}
