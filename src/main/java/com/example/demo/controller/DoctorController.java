package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DoctorDTO;
import com.example.demo.service.DoctorService;

@RestController
@RequestMapping(path = "/doctor")
public class DoctorController {
	DoctorService doctorService;

	@Autowired
	public DoctorController(DoctorService doctorService) {
		super();
		this.doctorService = doctorService;
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(doctorService.findById(id));

	}

	@PutMapping
	public ResponseEntity<Object> update(@RequestBody DoctorDTO dto) {

		doctorService.update(dto);
		return ResponseEntity.status(HttpStatus.OK).body("Doctor successfully updated!");
	}

	@PostMapping()
	public ResponseEntity<Object> save(@RequestBody DoctorDTO dto) {

		doctorService.save(dto);
		return ResponseEntity.status(HttpStatus.OK).body("Doctor successfully saved!");
	}

	@DeleteMapping
	public ResponseEntity<Object> delete(@RequestBody DoctorDTO dto) {

		doctorService.delete(dto);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Doctor successfully deleted!");
	}

	@PostMapping("/addExam")
	public @ResponseBody ResponseEntity<Object> addExam(@RequestParam Long patientId, @RequestParam Long doctorId,
			@RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime dateTime) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(doctorService.addExam(patientId, doctorId, dateTime));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@DeleteMapping("/removeExam")
	public @ResponseBody ResponseEntity<Object> removeExam(@RequestParam Long patientId, @RequestParam Long doctorId,
			@RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime dateTime) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(doctorService.removeExam(patientId, doctorId, dateTime));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

}
