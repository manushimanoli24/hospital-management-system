package com.hospital.hms.service;

import com.hospital.hms.entity.Doctor;
import com.hospital.hms.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<Doctor> searchDoctors(String search) {

        if (search == null || search.trim().isEmpty()) {
            return doctorRepository.findAll();
        }

        return doctorRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                        search,
                        search
                );
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}