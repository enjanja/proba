package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SpecializationDTO;
import com.example.demo.service.SpecializationService;

@RestController
@RequestMapping(path = "/specialization")
public class SpecializationController {

	@Autowired
	private SpecializationService specializationService;

	@PostMapping
	public String saveSpecialization(SpecializationDTO specialization) {
		specializationService.save(specialization);
		return "bravo";
	}
}
