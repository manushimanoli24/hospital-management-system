package com.hospital.hms.controller;

import com.hospital.hms.entity.Employee;
import com.hospital.hms.repository.EmployeeRepository;
import com.hospital.hms.repository.DepartmentRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeController(EmployeeRepository employeeRepository,
                              DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public String listEmployees(Model model) {

        model.addAttribute("employees", employeeRepository.findAll());

        return "employees";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {

        model.addAttribute("employee", new Employee());

        model.addAttribute("departments",
                departmentRepository.findAll());

        return "employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee) {

        employeeRepository.save(employee);

        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id,
                               Model model) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid employee ID"));

        model.addAttribute("employee", employee);

        model.addAttribute("departments",
                departmentRepository.findAll());

        return "employee-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {

        employeeRepository.deleteById(id);

        return "redirect:/employees";
    }
}