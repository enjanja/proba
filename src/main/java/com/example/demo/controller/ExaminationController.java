package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ExaminationDTO;
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

	@PostMapping
	public String save(@RequestBody ExaminationDTO dto) {

		examinationService.save(dto);
		return "bravo";
	}

	@GetMapping()
	public ResponseEntity<Object> getAll() {

		List<ExaminationDTO> examinations = examinationService.getAll();

		if (examinations.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no examinations currently.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(examinations);

	}

	@PutMapping()
	public ResponseEntity<Object> updateDiagnosis(@RequestBody UpdateDTO dto) {

		ExaminationDTO examination = new ExaminationDTO();
		try {
			examination = examinationService.updateDiagnosis(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.OK).body(examination);

	}

	@PutMapping("/change-date")
	public ResponseEntity<Object> updateDate(@RequestBody UpdateDTO dto) {

		ExaminationDTO examination = new ExaminationDTO();
//		try {
//			examination = examinationService.updateDate(dto);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		return ResponseEntity.status(HttpStatus.OK).body(examination);

	}
}
