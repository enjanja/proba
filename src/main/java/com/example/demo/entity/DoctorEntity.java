package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("2")
public class DoctorEntity extends UserEntity {

//	@ManyToOne
//	@JoinColumn(name = "specialization_id", nullable = false)
//	private SpecializationEntity specialization;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "hospital_doctor", joinColumns = @JoinColumn(name = "doctor_id"), inverseJoinColumns = @JoinColumn(name = "hospital_id"))
	Set<HospitalEntity> hospitals = new HashSet<>();

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.PERSIST)
	Set<ExaminationEntity> examinations = new HashSet<>();

	public DoctorEntity() {
		super();

	}

	public DoctorEntity(Long id, String username, String password, String name, Set<HospitalEntity> hospitals,
			Set<ExaminationEntity> examinations) {
		super(id, username, password, name);
		this.examinations = examinations;
		this.hospitals = hospitals;
	}

	public void addHospital(HospitalEntity hospital) {
		hospitals.add(hospital);
		hospital.getDoctors().add(this);
	}

	public void removeHospital(HospitalEntity hospital) {
		hospitals.remove(hospital);
		hospital.getDoctors().remove(this);
	}

	public void addExamination(ExaminationEntity examination) {
		examinations.add(examination);
	}

	public void removeExamination(ExaminationEntity examination) {
		examinations.remove(examination);
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
