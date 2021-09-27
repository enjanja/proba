package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.NurseDTO;
import com.example.demo.service.NurseService;

@RestController
@RequestMapping(path = "/nurse")
public class NurseController {
	NurseService nurseService;

	@Autowired
	public NurseController(NurseService nurseService) {
		this.nurseService = nurseService;
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<NurseDTO> dto = nurseService.findById(id);
		if (dto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(dto.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exam with id " + id + " does not exist!");
		}
	}

	@GetMapping
	public @ResponseBody ResponseEntity<List<NurseDTO>> getAll() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(nurseService.getAll());

	}

	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@RequestBody NurseDTO nurseDTO) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(nurseService.save(nurseDTO));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping
	public @ResponseBody ResponseEntity<Object> update(@RequestBody NurseDTO dto) {
		try {
			Optional<NurseDTO> nurse = nurseService.update(dto);
			if (nurse.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(dto);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

	}

}
