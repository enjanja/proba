package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hospital")
public class HospitalEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String address;
	private String name;

	@ManyToMany(mappedBy = "hospitals")
	Set<DoctorEntity> doctors;

	@OneToMany(mappedBy = "hospital")
	private Set<NurseEntity> nurse;

//	public void addDoctor(DoctorEntity doctor) {
//		doctors.add(doctor);
////		doctor.getHospitals().add(this);
//	}
//
//	public void removeDoctor(DoctorEntity doctor) {
//		doctors.remove(doctor);
////		doctor.getHospitals().remove(this);
//	}
}
