package com.hospital.hms.controller;

import com.hospital.hms.entity.Billing;
import com.hospital.hms.repository.BillingRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/billing")
public class BillingController {

    private final BillingRepository billingRepository;

    public BillingController(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }

    // ==============================
    // Billing List
    // ==============================

    @GetMapping
    public String billing(Model model) {

        model.addAttribute(
                "billings",
                billingRepository.findAll()
        );

        return "billing";
    }


    // ==============================
    // Add Billing Page
    // ==============================

    @GetMapping("/add")
    public String addBilling(Model model) {

        Billing billing = new Billing();

        // Automatically set today's date
        billing.setBillingDate(LocalDate.now());

        model.addAttribute(
                "billing",
                billing
        );

        return "add-billing";
    }


    // ==============================
    // Save Billing
    // ==============================

    @PostMapping("/save")
    public String saveBilling(
            @ModelAttribute Billing billing) {

        // If billing date is empty,
        // automatically use today's date
        if (billing.getBillingDate() == null) {
            billing.setBillingDate(LocalDate.now());
        }

        // Calculate total
        double total =
                billing.getConsultationCharge()
                + billing.getLaboratoryCharge()
                + billing.getPharmacyCharge()
                + billing.getAdmissionCharge();

        billing.setTotal(total);


        // Calculate payment status
        if (billing.getPayment() >= total) {

            billing.setPaymentStatus("PAID");

        } else if (billing.getPayment() > 0) {

            billing.setPaymentStatus("PARTIALLY PAID");

        } else {

            billing.setPaymentStatus("UNPAID");
        }


        billingRepository.save(billing);

        return "redirect:/billing";
    }


    // ==============================
    // View Receipt
    // ==============================

    @GetMapping("/receipt/{id}")
    public String receipt(
            @PathVariable Long id,
            Model model) {

        Billing billing =
                billingRepository.findById(id).orElse(null);

        if (billing == null) {
            return "redirect:/billing";
        }

        model.addAttribute(
                "billing",
                billing
        );

        return "billing-receipt";
    }


    // ==============================
    // Edit Bill
    // ==============================

    @GetMapping("/edit/{id}")
    public String editBilling(
            @PathVariable Long id,
            Model model) {

        Billing billing =
                billingRepository.findById(id).orElse(null);

        if (billing == null) {
            return "redirect:/billing";
        }

        model.addAttribute(
                "billing",
                billing
        );

        return "edit-billing";
    }


    // ==============================
    // Update Bill
    // ==============================

    @PostMapping("/update")
    public String updateBilling(
            @ModelAttribute Billing billing) {

        // Find existing bill
        Billing existingBilling =
                billingRepository.findById(billing.getId())
                        .orElse(null);

        if (existingBilling == null) {
            return "redirect:/billing";
        }


        // Keep Patient ID
        existingBilling.setPatientId(
                billing.getPatientId()
        );


        // Update Patient Name
        existingBilling.setPatientName(
                billing.getPatientName()
        );


        // Update Consultation Charge
        existingBilling.setConsultationCharge(
                billing.getConsultationCharge()
        );


        // Update Laboratory Charge
        existingBilling.setLaboratoryCharge(
                billing.getLaboratoryCharge()
        );


        // Update Pharmacy Charge
        existingBilling.setPharmacyCharge(
                billing.getPharmacyCharge()
        );


        // Update Admission Charge
        existingBilling.setAdmissionCharge(
                billing.getAdmissionCharge()
        );


        // Update Payment
        existingBilling.setPayment(
                billing.getPayment()
        );


        // Keep existing billing date
        // Only update it if a date was supplied
        if (billing.getBillingDate() != null) {

            existingBilling.setBillingDate(
                    billing.getBillingDate()
            );
        }


        // Calculate total again
        double total =
                existingBilling.getConsultationCharge()
                + existingBilling.getLaboratoryCharge()
                + existingBilling.getPharmacyCharge()
                + existingBilling.getAdmissionCharge();

        existingBilling.setTotal(total);


        // Update payment status
        if (existingBilling.getPayment() >= total) {

            existingBilling.setPaymentStatus("PAID");

        } else if (existingBilling.getPayment() > 0) {

            existingBilling.setPaymentStatus("PARTIALLY PAID");

        } else {

            existingBilling.setPaymentStatus("UNPAID");
        }


        // Save existing bill
        billingRepository.save(existingBilling);

        return "redirect:/billing";
    }


    // ==============================
    // Delete Bill
    // ==============================

    @GetMapping("/delete/{id}")
    public String deleteBilling(
            @PathVariable Long id) {

        billingRepository.deleteById(id);

        return "redirect:/billing";
    }
}