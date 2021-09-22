package com.example.demo.controller;

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
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient with id " + id + " does not exist!");
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

		doctorService.save(dto);
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

}
