package com.hospital.hms.controller;

import com.hospital.hms.entity.Doctor;
import com.hospital.hms.service.DoctorService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Display all doctors
    @GetMapping("/doctors")
    public String doctors(
            @RequestParam(required = false) String search,
            Model model) {

        model.addAttribute(
                "doctors",
                doctorService.searchDoctors(search)
        );

        model.addAttribute("search", search);

        return "doctors";
    }

    // Show Add Doctor page
    @GetMapping("/doctors/add")
    public String addDoctorForm(Model model) {

        model.addAttribute("doctor", new Doctor());

        return "add-doctor";
    }

    // Save Doctor
    @PostMapping("/doctors/add")
    public String addDoctor(
            @Valid @ModelAttribute("doctor") Doctor doctor,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "add-doctor";
        }

        doctorService.saveDoctor(doctor);

        return "redirect:/doctors";
    }

    // View Doctor
    @GetMapping("/doctors/view/{id}")
    public String viewDoctor(
            @PathVariable Long id,
            Model model) {

        Doctor doctor = doctorService
                .getDoctorById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid doctor ID: " + id));

        model.addAttribute("doctor", doctor);

        return "view-doctor";
    }

    // Show Edit Doctor page
    @GetMapping("/doctors/edit/{id}")
    public String editDoctor(
            @PathVariable Long id,
            Model model) {

        Doctor doctor = doctorService
                .getDoctorById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid doctor ID: " + id));

        model.addAttribute("doctor", doctor);

        return "edit-doctor";
    }

    // Update Doctor
    @PostMapping("/doctors/edit/{id}")
    public String updateDoctor(
            @PathVariable Long id,
            @Valid @ModelAttribute("doctor") Doctor doctor,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            doctor.setId(id);
            return "edit-doctor";
        }

        doctor.setId(id);

        doctorService.saveDoctor(doctor);

        return "redirect:/doctors";
    }

    // Delete Doctor
    @GetMapping("/doctors/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {

        doctorService.deleteDoctor(id);

        return "redirect:/doctors";
    }
}