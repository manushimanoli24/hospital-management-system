package com.hospital.hms.controller;

import com.hospital.hms.entity.LaboratoryTest;
import com.hospital.hms.repository.LaboratoryTestRepository;
import com.hospital.hms.repository.PatientRepository;
import com.hospital.hms.repository.DoctorRepository;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/laboratory")
public class LaboratoryController {

    private final LaboratoryTestRepository laboratoryTestRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public LaboratoryController(
            LaboratoryTestRepository laboratoryTestRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository) {

        this.laboratoryTestRepository = laboratoryTestRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    // Laboratory test list
    @GetMapping
    public String laboratory(Model model) {

        model.addAttribute(
                "laboratoryTests",
                laboratoryTestRepository.findAll()
        );

        return "laboratory";
    }

    // Add laboratory test page
    @GetMapping("/add")
    public String addLaboratoryTest(Model model) {

        LaboratoryTest laboratoryTest = new LaboratoryTest();

        laboratoryTest.setTestDate(LocalDate.now());
        laboratoryTest.setStatus("REQUESTED");

        model.addAttribute(
                "laboratoryTest",
                laboratoryTest
        );

        model.addAttribute(
                "patients",
                patientRepository.findAll()
        );

        model.addAttribute(
                "doctors",
                doctorRepository.findAll()
        );

        return "add-laboratory-test";
    }

    // Save laboratory test with validation
    @PostMapping("/save")
    public String saveLaboratoryTest(
            @Valid @ModelAttribute("laboratoryTest") LaboratoryTest laboratoryTest,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute(
                    "patients",
                    patientRepository.findAll()
            );

            model.addAttribute(
                    "doctors",
                    doctorRepository.findAll()
            );

            return "add-laboratory-test";
        }

        if (laboratoryTest.getTestDate() == null) {
            laboratoryTest.setTestDate(LocalDate.now());
        }

        if (laboratoryTest.getStatus() == null ||
                laboratoryTest.getStatus().isEmpty()) {

            laboratoryTest.setStatus("REQUESTED");
        }

        laboratoryTestRepository.save(laboratoryTest);

        return "redirect:/laboratory";
    }

    // Collect Sample
    @PostMapping("/collect/{id}")
    public String collectSample(
            @PathVariable Long id) {

        LaboratoryTest test =
                laboratoryTestRepository
                        .findById(id)
                        .orElse(null);

        if (test != null) {

            test.setStatus("SAMPLE_COLLECTED");

            laboratoryTestRepository.save(test);
        }

        return "redirect:/laboratory";
    }

    // Enter Result page
    @GetMapping("/result/{id}")
    public String enterResult(
            @PathVariable Long id,
            Model model) {

        LaboratoryTest test =
                laboratoryTestRepository
                        .findById(id)
                        .orElse(null);

        if (test == null) {
            return "redirect:/laboratory";
        }

        model.addAttribute(
                "laboratoryTest",
                test
        );

        return "enter-result";
    }

    // Save Result
    @PostMapping("/result/{id}")
    public String saveResult(
            @PathVariable Long id,
            @RequestParam("result") String result) {

        LaboratoryTest test =
                laboratoryTestRepository
                        .findById(id)
                        .orElse(null);

        if (test != null) {

            test.setResult(result);
            test.setStatus("RESULT_ENTERED");

            laboratoryTestRepository.save(test);
        }

        return "redirect:/laboratory";
    }

    // Generate / View Laboratory Report
    @GetMapping("/report/{id}")
    public String generateReport(
            @PathVariable Long id,
            Model model) {

        LaboratoryTest test =
                laboratoryTestRepository
                        .findById(id)
                        .orElse(null);

        if (test == null) {
            return "redirect:/laboratory";
        }

        model.addAttribute(
                "laboratoryTest",
                test
        );

        return "laboratory-report";
    }
}