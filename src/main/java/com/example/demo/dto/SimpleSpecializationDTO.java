package com.example.demo.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleSpecializationDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
}
