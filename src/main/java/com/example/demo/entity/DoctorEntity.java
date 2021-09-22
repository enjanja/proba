package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "doctor", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class DoctorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String username;

	private String password;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "hospital_doctor", joinColumns = @JoinColumn(name = "doctor_id"), inverseJoinColumns = @JoinColumn(name = "hospital_id"))
	Set<HospitalEntity> hospitals = new HashSet<>();

//	@ManyToOne
//	@JoinColumn(name = "specialization_id", nullable = false)
//	private SpecializationEntity specialization;
//
//	@OneToMany(mappedBy = "doctor")
//	Set<ExaminationEntity> examination;

	public void addHospital(HospitalEntity hospital) {
		hospitals.add(hospital);
		hospital.getDoctors().add(this);
	}

	public void removeHospital(HospitalEntity hospital) {
		hospitals.remove(hospital);
		hospital.getDoctors().remove(this);
	}

	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (!(object instanceof DoctorEntity))
			return false;
		return id != null && id.equals(((DoctorEntity) object).getId());

	};

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}