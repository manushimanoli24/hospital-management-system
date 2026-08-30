package com.hospital.hms.controller;

import com.hospital.hms.entity.MedicalRecord;
import com.hospital.hms.repository.MedicalRecordRepository;
import com.hospital.hms.repository.PatientRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/medical-records")
public class MedicalRecordController {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;

    public MedicalRecordController(
            MedicalRecordRepository medicalRecordRepository,
            PatientRepository patientRepository) {

        this.medicalRecordRepository = medicalRecordRepository;
        this.patientRepository = patientRepository;
    }


    // ==============================
    // Medical Records List
    // ==============================

    @GetMapping
    public String medicalRecords(Model model) {

        model.addAttribute(
                "medicalRecords",
                medicalRecordRepository.findAll()
        );

        return "medical-records";
    }


    // ==============================
    // Add Medical Record Page
    // ==============================

    @GetMapping("/add")
    public String addMedicalRecord(Model model) {

        MedicalRecord medicalRecord = new MedicalRecord();

        medicalRecord.setRecordDate(LocalDate.now());

        model.addAttribute(
                "medicalRecord",
                medicalRecord
        );

        model.addAttribute(
                "patients",
                patientRepository.findAll()
        );

        return "add-medical-record";
    }


    // ==============================
    // Save Medical Record
    // ==============================

    @PostMapping("/save")
    public String saveMedicalRecord(
            @ModelAttribute MedicalRecord medicalRecord) {

        if (medicalRecord.getRecordDate() == null) {
            medicalRecord.setRecordDate(LocalDate.now());
        }

        medicalRecordRepository.save(medicalRecord);

        return "redirect:/medical-records";
    }


    // ==============================
    // View Medical Record
    // ==============================

    @GetMapping("/view/{id}")
    public String viewMedicalRecord(
            @PathVariable Long id,
            Model model) {

        MedicalRecord medicalRecord =
                medicalRecordRepository.findById(id)
                        .orElseThrow(
                                () -> new IllegalArgumentException(
                                        "Invalid medical record ID: " + id
                                )
                        );

        model.addAttribute(
                "medicalRecord",
                medicalRecord
        );

        return "view-medical-record";
    }


    // ==============================
    // Edit Medical Record Page
    // ==============================

    @GetMapping("/edit/{id}")
    public String editMedicalRecord(
            @PathVariable Long id,
            Model model) {

        MedicalRecord medicalRecord =
                medicalRecordRepository.findById(id)
                        .orElseThrow(
                                () -> new IllegalArgumentException(
                                        "Invalid medical record ID: " + id
                                )
                        );

        model.addAttribute(
                "medicalRecord",
                medicalRecord
        );

        model.addAttribute(
                "patients",
                patientRepository.findAll()
        );

        return "edit-medical-record";
    }


    // ==============================
    // Delete Medical Record
    // ==============================

    @GetMapping("/delete/{id}")
    public String deleteMedicalRecord(
            @PathVariable Long id) {

        medicalRecordRepository.deleteById(id);

        return "redirect:/medical-records";
    }
}