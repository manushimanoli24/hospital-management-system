package com.hospital.hms.controller;

import com.hospital.hms.entity.Appointment;
import com.hospital.hms.repository.AppointmentRepository;
import com.hospital.hms.repository.PatientRepository;
import com.hospital.hms.repository.DoctorRepository;
import com.hospital.hms.repository.DepartmentRepository;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;

    public AppointmentController(
            AppointmentRepository appointmentRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository,
            DepartmentRepository departmentRepository) {

        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.departmentRepository = departmentRepository;
    }

    // Show appointment list
    @GetMapping
    public String appointments(Model model) {

        model.addAttribute(
                "appointments",
                appointmentRepository.findAll()
        );

        return "appointments";
    }

    // Show Add Appointment page
    @GetMapping("/add")
    public String addAppointment(Model model) {

        model.addAttribute(
                "appointment",
                new Appointment()
        );

        model.addAttribute(
                "patients",
                patientRepository.findAll()
        );

        model.addAttribute(
                "doctors",
                doctorRepository.findAll()
        );

        model.addAttribute(
                "departments",
                departmentRepository.findAll()
        );

        return "add-appointment";
    }

    // Book appointment with validation
    @PostMapping("/book")
    public String bookAppointment(
            @Valid @ModelAttribute("appointment") Appointment appointment,
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

            model.addAttribute(
                    "departments",
                    departmentRepository.findAll()
            );

            return "add-appointment";
        }

        appointment.setStatus("BOOKED");

        appointmentRepository.save(appointment);

        return "redirect:/appointments";
    }

    // Cancel appointment
    @PostMapping("/cancel/{id}")
    public String cancelAppointment(
            @PathVariable Long id) {

        Appointment appointment =
                appointmentRepository
                        .findById(id)
                        .orElse(null);

        if (appointment != null) {

            appointment.setStatus("CANCELLED");

            appointmentRepository.save(appointment);
        }

        return "redirect:/appointments";
    }
}