package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PatientDTO;
import com.example.demo.service.PatientService;

@RestController
@RequestMapping(path = "/patient")
public class PatientController {

	PatientService patientService;

	@Autowired
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<PatientDTO> dto = patientService.findById(id);
		if (dto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(dto.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient with id " + id + " does not exist!");
		}
	}

	@GetMapping
	public @ResponseBody ResponseEntity<List<PatientDTO>> getAll() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(patientService.getAll());

	}

	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@RequestBody PatientDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(patientService.save(dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping
	public @ResponseBody ResponseEntity<Object> update(@RequestBody PatientDTO patient) {
		try {
			PatientDTO dto = patientService.update(patient);
			if (dto != null) {
				return ResponseEntity.status(HttpStatus.OK).body(dto);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> delete(@PathVariable(name = "id") Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(patientService.delete(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
