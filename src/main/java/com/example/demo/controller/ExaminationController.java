package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UpdateDTO;
import com.example.demo.service.ExaminationService;

@RestController
@RequestMapping("/examinations")
public class ExaminationController {

	ExaminationService examinationService;

	@Autowired
	public ExaminationController(ExaminationService examinationService) {
		this.examinationService = examinationService;
	}

	/**
	 * Returns a list of all existing doctors.
	 */
	@GetMapping()
	public ResponseEntity<Object> getAll() {

		return ResponseEntity.status(HttpStatus.OK).body(examinationService.getAll());
	}

	/**
	 * Updates diagnosis in an existing examination.
	 * 
	 * @param dto object containing information about examination
	 * @return
	 */
	@PutMapping()
	public ResponseEntity<Object> updateDiagnosis(@RequestBody UpdateDTO dto) {
		examinationService.updateDiagnosis(dto);
		return ResponseEntity.status(HttpStatus.OK).body("Successfully added diagnosis.");
	}

}
