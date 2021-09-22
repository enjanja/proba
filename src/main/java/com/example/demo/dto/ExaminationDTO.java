package com.example.demo.dto;

import java.io.Serializable;
import java.time.LocalDate;

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

	private int id;
//	private DoctorSimpleDTO doctor;
//	private PatientSimpleDTO patient;
	private DoctorDTO doctor;
	private PatientDTO patient;
	private LocalDate date;
	private String diagnosis;
}
