package com.example.demo.dto;

import java.io.Serializable;

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
//	private DoctorSimpleDTO doctor;
//	private PatientSimpleDTO patient;
	private DoctorDTO doctor;
	private PatientDTO patient;
//	private LocalDate date;
	private String diagnosis;
}
