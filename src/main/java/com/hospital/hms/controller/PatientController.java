package com.hospital.hms.controller;

import com.hospital.hms.entity.Patient;
import com.hospital.hms.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Display all patients
    @GetMapping("/patients")
    public String patients(
            @RequestParam(required = false) String search,
            Model model) {

        model.addAttribute(
                "patients",
                patientService.searchPatients(search)
        );

        model.addAttribute("search", search);

        return "patients";
    }

    // Show Add Patient page
    @GetMapping("/patients/add")
    public String addPatientForm(Model model) {

        model.addAttribute("patient", new Patient());

        return "add-patient";
    }

    // Save new patient
    @PostMapping("/patients/add")
    public String addPatient(@ModelAttribute Patient patient) {

        patientService.savePatient(patient);

        return "redirect:/patients";
    }

    // Show Edit Patient page
    @GetMapping("/patients/edit/{id}")
    public String editPatient(
            @PathVariable Long id,
            Model model) {

        Patient patient = patientService
                .getPatientById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid patient ID: " + id
                        ));

        model.addAttribute("patient", patient);

        return "edit-patient";
    }

    // Update patient
    @PostMapping("/patients/edit/{id}")
    public String updatePatient(
            @PathVariable Long id,
            @ModelAttribute Patient patient) {

        patient.setId(id);

        patientService.savePatient(patient);

        return "redirect:/patients";
    }

    // View patient
    @GetMapping("/patients/view/{id}")
    public String viewPatient(
            @PathVariable Long id,
            Model model) {

        Patient patient = patientService
                .getPatientById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid patient ID: " + id
                        ));

        model.addAttribute("patient", patient);

        return "view-patient";
    }

    // Delete patient
    @GetMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable Long id) {

        patientService.deletePatient(id);

        return "redirect:/patients";
    }
}