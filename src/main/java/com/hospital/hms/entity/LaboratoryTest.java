package com.hospital.hms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "laboratory_tests")
public class LaboratoryTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Please select a patient")
    private Long patientId;

    @NotNull(message = "Please select a doctor")
    private Long doctorId;

    @NotBlank(message = "Test name is required")
    @Size(max = 100, message = "Test name must not exceed 100 characters")
    @Column(nullable = false)
    private String testName;

    @Size(max = 100, message = "Sample must not exceed 100 characters")
    private String sample;

    @Size(max = 1000, message = "Result must not exceed 1000 characters")
    @Column(columnDefinition = "TEXT")
    private String result;

    @NotNull(message = "Test date is required")
    private LocalDate testDate;

    @NotBlank(message = "Status is required")
    private String status;

    public LaboratoryTest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}