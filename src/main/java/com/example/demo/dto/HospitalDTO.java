package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;

	@NotNull
	@NotBlank
	private String address;

	@NotNull
	@NotBlank
	private String name;
}
