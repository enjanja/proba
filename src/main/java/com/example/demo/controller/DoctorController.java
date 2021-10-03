package com.example.demo.controller;

import java.time.LocalDate;
import java.util.Optional;

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
		Optional<DoctorDTO> dto = doctorService.findById(id);
		if (dto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(dto.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor with id " + id + " does not exist!");
		}
	}

	@PutMapping
	public String update(@RequestBody DoctorDTO dto) {
		try {
			doctorService.update(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "bravo.";
	}

	@PostMapping()
	public String save(@RequestBody DoctorDTO dto) {
		try {
			doctorService.save(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "bravo";
	}

	@DeleteMapping
	public String delete(@RequestBody DoctorDTO dto) {

		try {
			doctorService.delete(dto);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "bravo";
	}

	@PostMapping("/addExam")
	public @ResponseBody ResponseEntity<Object> addExam(@RequestParam Long patientId, @RequestParam Long doctorId,
			@RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(doctorService.addExam(patientId, doctorId, date));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@PostMapping("/removeExam")
	public @ResponseBody ResponseEntity<Object> removeExam(@RequestParam Long patientId, @RequestParam Long doctorId,
			@RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(doctorService.removeExam(patientId, doctorId, date));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

}
