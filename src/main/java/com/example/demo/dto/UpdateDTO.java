package com.example.demo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDTO {

	Long doctorId;
	Long patientId;
	LocalDate date;
	LocalDate newDate;
	String Diagnosis;
}
