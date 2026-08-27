package com.hospital.hms.repository;

import com.hospital.hms.entity.LaboratoryTest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratoryTestRepository
        extends JpaRepository<LaboratoryTest, Long> {

}