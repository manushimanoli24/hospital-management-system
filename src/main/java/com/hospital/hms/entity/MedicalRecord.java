package com.hospital.hms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "medical_records")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Patient is required")
    @Min(value = 1, message = "Patient ID must be greater than 0")
    private Long patientId;

    @NotBlank(message = "Diagnosis is required")
    @Size(max = 1000, message = "Diagnosis cannot exceed 1000 characters")
    @Column(columnDefinition = "TEXT")
    private String diagnosis;

    @Size(max = 2000, message = "Treatment cannot exceed 2000 characters")
    @Column(columnDefinition = "TEXT")
    private String treatment;

    @Size(max = 2000, message = "Prescription cannot exceed 2000 characters")
    @Column(columnDefinition = "TEXT")
    private String prescription;

    @Size(max = 5000, message = "Medical report cannot exceed 5000 characters")
    @Column(columnDefinition = "TEXT")
    private String medicalReport;

    @NotNull(message = "Record date is required")
    private LocalDate recordDate;

    public MedicalRecord() {
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getMedicalReport() {
        return medicalReport;
    }

    public void setMedicalReport(String medicalReport) {
        this.medicalReport = medicalReport;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }
}