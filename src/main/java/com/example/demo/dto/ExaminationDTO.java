package com.example.demo.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.example.demo.entity.ExaminationId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExaminationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ExaminationId id;
	private DoctorSimpleDTO doctor;
	private PatientSimpleDTO patient;
//	private LocalDate date;
	private String diagnosis;

	public ExaminationDTO(DoctorSimpleDTO doctor, PatientSimpleDTO patient, LocalDate date, String diagnosis) {
		super();
		this.id = new ExaminationId(doctor.getId(), patient.getId(), date);
//		this.date = date;
		this.diagnosis = diagnosis;
		this.doctor = doctor;
		this.patient = patient;
	}

}
