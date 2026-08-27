package com.hospital.hms.controller;

import com.hospital.hms.entity.Billing;
import com.hospital.hms.entity.Medicine;
import com.hospital.hms.entity.Employee;

import com.hospital.hms.repository.PatientRepository;
import com.hospital.hms.repository.AppointmentRepository;
import com.hospital.hms.repository.BillingRepository;
import com.hospital.hms.repository.MedicineRepository;
import com.hospital.hms.repository.LaboratoryTestRepository;
import com.hospital.hms.repository.EmployeeRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportsController {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final BillingRepository billingRepository;
    private final MedicineRepository medicineRepository;
    private final LaboratoryTestRepository laboratoryTestRepository;
    private final EmployeeRepository employeeRepository;


    // ==============================
    // Constructor
    // ==============================

    public ReportsController(
            PatientRepository patientRepository,
            AppointmentRepository appointmentRepository,
            BillingRepository billingRepository,
            MedicineRepository medicineRepository,
            LaboratoryTestRepository laboratoryTestRepository,
            EmployeeRepository employeeRepository) {

        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.billingRepository = billingRepository;
        this.medicineRepository = medicineRepository;
        this.laboratoryTestRepository = laboratoryTestRepository;
        this.employeeRepository = employeeRepository;
    }


    // ==============================
    // Main Reports Page
    // ==============================

    @GetMapping("/reports")
    public String reports() {

        return "reports";
    }


    // ==============================
    // Patient Reports
    // ==============================

    @GetMapping("/reports/patients")
    public String patientReports(Model model) {

        long totalPatients =
                patientRepository.count();

        model.addAttribute(
                "totalPatients",
                totalPatients
        );

        return "patient-reports";
    }


    // ==============================
    // Appointment Reports
    // ==============================

    @GetMapping("/reports/appointments")
    public String appointmentReports(Model model) {

        long totalAppointments =
                appointmentRepository.count();

        model.addAttribute(
                "totalAppointments",
                totalAppointments
        );

        return "appointment-reports";
    }


    // ==============================
    // Revenue Reports
    // ==============================

    @GetMapping("/reports/revenue")
    public String revenueReports(Model model) {

        var billings =
                billingRepository.findAll();


        // Total Revenue

        double totalRevenue =
                billings.stream()
                        .mapToDouble(Billing::getTotal)
                        .sum();


        // Paid Revenue

        double paidRevenue =
                billings.stream()
                        .filter(billing ->
                                billing.getPaymentStatus() != null &&
                                billing.getPaymentStatus()
                                        .equalsIgnoreCase("PAID"))
                        .mapToDouble(Billing::getTotal)
                        .sum();


        // Pending Revenue

        double pendingRevenue =
                billings.stream()
                        .filter(billing ->
                                billing.getPaymentStatus() != null &&
                                billing.getPaymentStatus()
                                        .equalsIgnoreCase("PENDING"))
                        .mapToDouble(Billing::getTotal)
                        .sum();


        // Consultation Revenue

        double consultationRevenue =
                billings.stream()
                        .mapToDouble(
                                Billing::getConsultationCharge)
                        .sum();


        // Laboratory Revenue

        double laboratoryRevenue =
                billings.stream()
                        .mapToDouble(
                                Billing::getLaboratoryCharge)
                        .sum();


        // Pharmacy Revenue

        double pharmacyRevenue =
                billings.stream()
                        .mapToDouble(
                                Billing::getPharmacyCharge)
                        .sum();


        // Admission Revenue

        double admissionRevenue =
                billings.stream()
                        .mapToDouble(
                                Billing::getAdmissionCharge)
                        .sum();


        // Send values to HTML

        model.addAttribute(
                "totalRevenue",
                totalRevenue
        );

        model.addAttribute(
                "paidRevenue",
                paidRevenue
        );

        model.addAttribute(
                "pendingRevenue",
                pendingRevenue
        );

        model.addAttribute(
                "consultationRevenue",
                consultationRevenue
        );

        model.addAttribute(
                "laboratoryRevenue",
                laboratoryRevenue
        );

        model.addAttribute(
                "pharmacyRevenue",
                pharmacyRevenue
        );

        model.addAttribute(
                "admissionRevenue",
                admissionRevenue
        );


        return "revenue-reports";
    }


    // ==============================
    // Pharmacy Reports
    // ==============================

    @GetMapping("/reports/pharmacy")
    public String pharmacyReports(Model model) {

        var medicines =
                medicineRepository.findAll();


        // Total Medicines

        long totalMedicines =
                medicines.size();


        // Available Stock

        int availableStock =
                medicines.stream()
                        .mapToInt(Medicine::getStock)
                        .sum();


        // Low Stock Medicines

        long lowStockMedicines =
                medicines.stream()
                        .filter(medicine ->
                                medicine.getStock() <= 10)
                        .count();


        // Expired Medicines

        long expiredMedicines =
                medicines.stream()
                        .filter(medicine ->
                                medicine.getExpiryDate() != null &&
                                medicine.getExpiryDate()
                                        .isBefore(
                                                java.time.LocalDate.now()
                                        ))
                        .count();


        // Send values to HTML

        model.addAttribute(
                "totalMedicines",
                totalMedicines
        );

        model.addAttribute(
                "availableStock",
                availableStock
        );

        model.addAttribute(
                "lowStockMedicines",
                lowStockMedicines
        );

        model.addAttribute(
                "expiredMedicines",
                expiredMedicines
        );


        return "pharmacy-reports";
    }


    // ==============================
    // Laboratory Reports
    // ==============================

    @GetMapping("/reports/laboratory")
    public String laboratoryReports(Model model) {

        var laboratoryTests =
                laboratoryTestRepository.findAll();


        // Total Tests

        long totalTests =
                laboratoryTests.size();


        // Requested Tests

        long requestedTests =
                laboratoryTests.stream()
                        .filter(test ->
                                test.getStatus() != null &&
                                test.getStatus()
                                        .equalsIgnoreCase(
                                                "REQUESTED"))
                        .count();


        // Sample Collected

        long sampleCollectedTests =
                laboratoryTests.stream()
                        .filter(test ->
                                test.getStatus() != null &&
                                test.getStatus()
                                        .equalsIgnoreCase(
                                                "SAMPLE_COLLECTED"))
                        .count();


        // Result Entered

        long resultEnteredTests =
                laboratoryTests.stream()
                        .filter(test ->
                                test.getStatus() != null &&
                                test.getStatus()
                                        .equalsIgnoreCase(
                                                "RESULT_ENTERED"))
                        .count();


        // Completed Tests

        long completedTests =
                laboratoryTests.stream()
                        .filter(test ->
                                test.getStatus() != null &&
                                test.getStatus()
                                        .equalsIgnoreCase(
                                                "COMPLETED"))
                        .count();


        // Send values to HTML

        model.addAttribute(
                "totalTests",
                totalTests
        );

        model.addAttribute(
                "requestedTests",
                requestedTests
        );

        model.addAttribute(
                "sampleCollectedTests",
                sampleCollectedTests
        );

        model.addAttribute(
                "resultEnteredTests",
                resultEnteredTests
        );

        model.addAttribute(
                "completedTests",
                completedTests
        );


        return "laboratory-reports";
    }


    // ==============================
    // Staff Reports
    // ==============================

    @GetMapping("/reports/staff")
    public String staffReports(Model model) {

        var employees =
                employeeRepository.findAll();


        // ==============================
        // Total Employees
        // ==============================

        long totalEmployees =
                employees.size();


        // ==============================
        // Employees by Position
        // ==============================

        var employeesByPosition =
                employees.stream()
                        .filter(employee ->
                                employee.getPosition() != null &&
                                !employee.getPosition().isBlank())
                        .collect(
                                java.util.stream.Collectors.groupingBy(
                                        Employee::getPosition,
                                        java.util.stream.Collectors.counting()
                                )
                        );


        // ==============================
        // Employees by Department
        // ==============================

        var employeesByDepartment =
                employees.stream()
                        .filter(employee ->
                                employee.getDepartment() != null)
                        .collect(
                                java.util.stream.Collectors.groupingBy(
                                        employee ->
                                                employee.getDepartment()
                                                        .getName(),
                                        java.util.stream.Collectors.counting()
                                )
                        );


        // ==============================
        // Send values to HTML
        // ==============================

        model.addAttribute(
                "totalEmployees",
                totalEmployees
        );

        model.addAttribute(
                "employeesByPosition",
                employeesByPosition
        );

        model.addAttribute(
                "employeesByDepartment",
                employeesByDepartment
        );


        return "staff-reports";
    }

}