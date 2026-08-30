package com.hospital.hms.controller;

import com.hospital.hms.entity.Medicine;
import com.hospital.hms.repository.MedicineRepository;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pharmacy")
public class PharmacyController {

    private final MedicineRepository medicineRepository;

    public PharmacyController(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    // Pharmacy page - View all medicines
    @GetMapping
    public String pharmacy(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            Model model) {

        if (name == null) {
            name = "";
        }

        if (category == null) {
            category = "";
        }

        if (!name.isEmpty() && !category.isEmpty()) {

            model.addAttribute(
                    "medicines",
                    medicineRepository
                            .findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(
                                    name,
                                    category
                            )
            );

        } else if (!name.isEmpty()) {

            model.addAttribute(
                    "medicines",
                    medicineRepository.findByNameContainingIgnoreCase(name)
            );

        } else if (!category.isEmpty()) {

            model.addAttribute(
                    "medicines",
                    medicineRepository.findByCategoryContainingIgnoreCase(category)
            );

        } else {

            model.addAttribute(
                    "medicines",
                    medicineRepository.findAll()
            );
        }

        model.addAttribute("searchName", name);
        model.addAttribute("searchCategory", category);

        return "pharmacy";
    }


    // Add medicine page
    @GetMapping("/add")
    public String addMedicine(Model model) {

        model.addAttribute(
                "medicine",
                new Medicine()
        );

        return "add-medicine";
    }


    // Save medicine with validation
    @PostMapping("/save")
    public String saveMedicine(
            @Valid @ModelAttribute("medicine") Medicine medicine,
            BindingResult bindingResult) {

        // If validation fails, return to add page
        if (bindingResult.hasErrors()) {
            return "add-medicine";
        }

        medicineRepository.save(medicine);

        return "redirect:/pharmacy";
    }


    // Edit medicine page
    @GetMapping("/edit/{id}")
    public String editMedicine(
            @PathVariable Long id,
            Model model) {

        Medicine medicine = medicineRepository
                .findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid medicine ID: " + id
                        )
                );

        model.addAttribute("medicine", medicine);

        return "edit-medicine";
    }


    // Update medicine with validation
    @PostMapping("/update/{id}")
    public String updateMedicine(
            @PathVariable Long id,
            @Valid @ModelAttribute("medicine") Medicine medicine,
            BindingResult bindingResult) {

        // Keep the existing ID
        medicine.setId(id);

        // If validation fails, return to edit page
        if (bindingResult.hasErrors()) {
            return "edit-medicine";
        }

        medicineRepository.save(medicine);

        return "redirect:/pharmacy";
    }


    // Delete medicine
    @GetMapping("/delete/{id}")
    public String deleteMedicine(
            @PathVariable Long id) {

        medicineRepository.deleteById(id);

        return "redirect:/pharmacy";
    }
}