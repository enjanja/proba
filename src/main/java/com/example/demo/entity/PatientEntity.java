package com.example.demo.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "patient", uniqueConstraints = { @UniqueConstraint(columnNames = { "jmbg" }) })
@NaturalIdCache
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PatientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String jmbg;
	private String name;

	public PatientEntity(String jmbg, String name) {
		super();
		this.jmbg = jmbg;
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PatientEntity patient = (PatientEntity) o;
		return Objects.equals(jmbg, patient.jmbg);
	}

	@Override
	public int hashCode() {
		return Objects.hash(jmbg);
	}
}
