package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ExaminationEntity;

@Repository
public interface ExaminationRepository extends JpaRepository<ExaminationEntity, Integer> {

}
