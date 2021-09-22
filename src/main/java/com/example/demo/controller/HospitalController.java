package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.HospitalDTO;
import com.example.demo.service.HospitalService;

@RestController
@RequestMapping(path = "/hospital")
public class HospitalController {

	private HospitalService hospitalService;

	@Autowired
	public HospitalController(HospitalService hospitalService) {
		this.hospitalService = hospitalService;
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<HospitalDTO> dto = hospitalService.findById(id);
		if (dto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(dto.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exam with id " + id + " does not exist!");
		}
	}

	@GetMapping
	public @ResponseBody ResponseEntity<List<HospitalDTO>> getAll() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(hospitalService.getAll());
	}

	@PostMapping
	public String save(@RequestBody HospitalDTO dto) {

		hospitalService.save(dto);
		return "successful";
	}

}
