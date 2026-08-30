package com.hospital.hms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "billings")
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Patient ID is required")
    @Min(value = 1, message = "Patient ID must be greater than 0")
    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @NotBlank(message = "Patient name is required")
    private String patientName;

    @PositiveOrZero(message = "Consultation charge cannot be negative")
    private double consultationCharge;

    @PositiveOrZero(message = "Laboratory charge cannot be negative")
    private double laboratoryCharge;

    @PositiveOrZero(message = "Pharmacy charge cannot be negative")
    private double pharmacyCharge;

    @PositiveOrZero(message = "Admission charge cannot be negative")
    private double admissionCharge;

    private double total;

    @PositiveOrZero(message = "Payment cannot be negative")
    private double payment;

    private String paymentStatus;

    @Column(name = "billing_date")
    private LocalDate billingDate;

    public Billing() {
    }

    public Long getId() {
        return id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public double getConsultationCharge() {
        return consultationCharge;
    }

    public void setConsultationCharge(double consultationCharge) {
        this.consultationCharge = consultationCharge;
    }

    public double getLaboratoryCharge() {
        return laboratoryCharge;
    }

    public void setLaboratoryCharge(double laboratoryCharge) {
        this.laboratoryCharge = laboratoryCharge;
    }

    public double getPharmacyCharge() {
        return pharmacyCharge;
    }

    public void setPharmacyCharge(double pharmacyCharge) {
        this.pharmacyCharge = pharmacyCharge;
    }

    public double getAdmissionCharge() {
        return admissionCharge;
    }

    public void setAdmissionCharge(double admissionCharge) {
        this.admissionCharge = admissionCharge;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(LocalDate billingDate) {
        this.billingDate = billingDate;
    }
}