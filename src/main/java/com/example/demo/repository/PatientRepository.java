package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PatientEntity;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Integer> {

}