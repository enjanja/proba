package com.example.demo.controller;

import java.util.List;

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

	/**
	 * Gets patient from db.
	 * 
	 * @param id patient id
	 */
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(patientService.findById(id));
	}

	/**
	 * Returns the list of all saved patients.
	 */
	@GetMapping
	public @ResponseBody ResponseEntity<List<PatientDTO>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(patientService.getAll());

	}

	/**
	 * Saves patient in db.
	 * 
	 * @param dto object containing info about patient
	 */
	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@RequestBody PatientDTO dto) {
		patientService.save(dto);
		return ResponseEntity.status(HttpStatus.OK).body("Patient successfully saved.");

	}

	/**
	 * Updates patient in db.
	 * 
	 * @param patient object containing info about patient
	 */
	@PutMapping
	public @ResponseBody ResponseEntity<Object> update(@RequestBody PatientDTO patient) {

		patientService.update(patient);
		return ResponseEntity.status(HttpStatus.OK).body("Patient successfully updated.");
	}

	/**
	 * Deletes patient from db.
	 * 
	 * @param id patient id
	 */
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> delete(@PathVariable(name = "id") Long id) {
		patientService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Patient successfully deleted.");

	}

}
