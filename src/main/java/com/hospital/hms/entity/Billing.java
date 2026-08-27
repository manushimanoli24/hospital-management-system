package com.hospital.hms.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "billings")
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    private String patientName;

    private double consultationCharge;

    private double laboratoryCharge;

    private double pharmacyCharge;

    private double admissionCharge;

    private double total;

    private double payment;

    private String paymentStatus;

    @Column(name = "billing_date")
    private LocalDate billingDate;

    public Billing() {
    }

    // ID
    public Long getId() {
        return id;
    }

    // Patient ID
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    // Patient Name
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    // Consultation Charge
    public double getConsultationCharge() {
        return consultationCharge;
    }

    public void setConsultationCharge(double consultationCharge) {
        this.consultationCharge = consultationCharge;
    }

    // Laboratory Charge
    public double getLaboratoryCharge() {
        return laboratoryCharge;
    }

    public void setLaboratoryCharge(double laboratoryCharge) {
        this.laboratoryCharge = laboratoryCharge;
    }

    // Pharmacy Charge
    public double getPharmacyCharge() {
        return pharmacyCharge;
    }

    public void setPharmacyCharge(double pharmacyCharge) {
        this.pharmacyCharge = pharmacyCharge;
    }

    // Admission Charge
    public double getAdmissionCharge() {
        return admissionCharge;
    }

    public void setAdmissionCharge(double admissionCharge) {
        this.admissionCharge = admissionCharge;
    }

    // Total
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // Payment
    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    // Payment Status
    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    // Billing Date
    public LocalDate getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(LocalDate billingDate) {
        this.billingDate = billingDate;
    }
}