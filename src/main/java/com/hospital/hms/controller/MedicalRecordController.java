package com.hospital.hms.controller;

import com.hospital.hms.entity.MedicalRecord;
import com.hospital.hms.repository.MedicalRecordRepository;
import com.hospital.hms.repository.PatientRepository;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    // Medical Records list
    @GetMapping
    public String medicalRecords(Model model) {

        model.addAttribute(
                "medicalRecords",
                medicalRecordRepository.findAll()
        );

        return "medical-records";
    }

    // Add Medical Record page
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

    // Save Medical Record
    @PostMapping("/save")
    public String saveMedicalRecord(
            @Valid @ModelAttribute("medicalRecord") MedicalRecord medicalRecord,
            BindingResult result,
            Model model) {

        // Check validation errors
        if (result.hasErrors()) {

            model.addAttribute(
                    "patients",
                    patientRepository.findAll()
            );

            return "add-medical-record";
        }

        if (medicalRecord.getRecordDate() == null) {
            medicalRecord.setRecordDate(LocalDate.now());
        }

        medicalRecordRepository.save(medicalRecord);

        return "redirect:/medical-records";
    }
}