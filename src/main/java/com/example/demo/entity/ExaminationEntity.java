package com.example.demo.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "examination")
public class ExaminationEntity {

	@EmbeddedId
	private ExaminationId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("patient_id")
	private PatientEntity patient;

	@ManyToOne
	@MapsId("doctor_id")
	private DoctorEntity doctor;

//	private LocalDate date;

	private String diagnosis;

	public ExaminationEntity(DoctorEntity doctor, PatientEntity patient, LocalDate date, String diagnosis) {
		super();
		this.id = new ExaminationId(doctor.getId(), patient.getId(), date);
		this.patient = patient;
		this.doctor = doctor;
//		this.date = date;
		this.diagnosis = diagnosis;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		ExaminationEntity that = (ExaminationEntity) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
