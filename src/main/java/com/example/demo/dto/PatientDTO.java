package com.example.demo.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;

	@NotNull
	@NotBlank
	@Size(min = 13, max = 13)
	private String jmbg;

	@NotNull
	@Size(min = 2)
	@NotBlank
	private String name;

	private Set<ExaminationDTO> examinations = new HashSet<>();
}
