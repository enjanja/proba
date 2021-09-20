package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctor", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class DoctorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@ManyToMany(mappedBy = "doctors")
	Set<HospitalEntity> hospitals;

	@ManyToOne
	@JoinColumn(name = "specialization_id", nullable = false)
	private SpecializationEntity specialization;
//	private int specialization_id; // Specialization specialization

	@OneToMany(mappedBy = "doctor")
	Set<ExaminationEntity> examination;

	private String username;

	private String password;

}
