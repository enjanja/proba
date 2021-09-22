package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ExaminationDTO;
import com.example.demo.service.ExaminationService;

@RestController
@RequestMapping("/examination")
public class ExaminationController {

	ExaminationService examinationService;

	@Autowired
	public ExaminationController(ExaminationService examinationService) {
		this.examinationService = examinationService;
	}

	@PostMapping()
	public String save(@RequestBody ExaminationDTO dto) {

		examinationService.save(dto);
		return "bravo";
	}
}
