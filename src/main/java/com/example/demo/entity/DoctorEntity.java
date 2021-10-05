package com.example.demo.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("2")
public class DoctorEntity extends UserEntity {

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "hospital_doctor", joinColumns = @JoinColumn(name = "doctor_id"), inverseJoinColumns = @JoinColumn(name = "hospital_id"))
	Set<HospitalEntity> hospitals = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "specialization_id", nullable = false)
	private SpecializationEntity specialization;

	@JsonIgnore
	@OneToMany(mappedBy = "doctor", cascade = { CascadeType.MERGE, CascadeType.PERSIST }, orphanRemoval = true)
	Set<ExaminationEntity> examinations = new HashSet<>();

	public DoctorEntity(Long id, String name, String username, String password, Set<HospitalEntity> hospitals) {
		super(id, username, password, name);
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

	public void addExamination(PatientEntity patient, LocalDate date) {
		ExaminationEntity examination = new ExaminationEntity(this, patient, date, "");
		examinations.add(examination);
		patient.getExaminations().add(examination);
	}

	public void removeExamination(PatientEntity patient, LocalDate date) {
		for (Iterator<ExaminationEntity> iterator = examinations.iterator(); iterator.hasNext();) {
			ExaminationEntity examinationEntity = iterator.next();

			if (examinationEntity.getDoctor().equals(this) && examinationEntity.getPatient().equals(patient)
					&& examinationEntity.getId().getDate().equals(date)) {
				iterator.remove();
				examinationEntity.getPatient().getExaminations().remove(examinationEntity);
				examinationEntity.setDoctor(null);
				examinationEntity.setPatient(null);
			}

		}
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
