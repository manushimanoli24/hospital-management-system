package com.hospital.hms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("totalPatients", 250);
        model.addAttribute("todayAppointments", 35);
        model.addAttribute("revenue", 250000);
        model.addAttribute("laboratoryRequests", 12);

        return "dashboard";
    }
}