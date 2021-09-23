package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ExaminationEntity;
import com.example.demo.entity.ExaminationId;

@Repository
public interface ExaminationRepository extends JpaRepository<ExaminationEntity, ExaminationId> {

}
