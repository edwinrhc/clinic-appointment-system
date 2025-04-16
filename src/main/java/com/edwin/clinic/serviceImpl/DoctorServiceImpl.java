package com.edwin.clinic.serviceImpl;

import com.edwin.clinic.constants.ClinicConstants;
import com.edwin.clinic.dto.DoctorDTO;
import com.edwin.clinic.entity.Doctor;
import com.edwin.clinic.jwt.JwtFilter;
import com.edwin.clinic.repository.DoctorRepository;
import com.edwin.clinic.service.DoctorService;
import com.edwin.clinic.utils.ClinicUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;


    @Autowired
    JwtFilter jwtFilter;


    @Override
    public ResponseEntity<String> addNewDoctor(DoctorDTO doctorDTO) {

        log.info("Trying to register new doctor:  {}", doctorDTO);
        try {
            if (jwtFilter.isAdmin()) {
                Doctor validEmail = doctorRepository.findByEmail(doctorDTO.getEmail());
                if (Objects.isNull(validEmail)) {
                    Doctor doctor = toEntity(doctorDTO);
                    doctorRepository.save(doctor);
                    return ClinicUtils.getResponseEntity("Doctor added successfully", HttpStatus.CREATED);
                } else {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
                }
            } else {
                return ClinicUtils.getResponseEntity(ClinicConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ClinicUtils.getResponseEntity(ClinicConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Doctor toEntity(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorDTO.getFirstName());
        doctor.setLastName(doctorDTO.getLastName());
        doctor.setSpecialty(doctorDTO.getSpecialty());
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setPhone(doctorDTO.getPhone());
        doctor.setStatus("true");
        return doctor;
    }


    @Override
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {

        try {
            if (jwtFilter.isAdmin()) {
                return new ResponseEntity<>(doctorRepository.getAllDoctors(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(doctorRepository.getAllDoctors(), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<?> getDoctorBySpecialty(String specialty) {
        try {
            if (jwtFilter.isAdmin()) {
                List<DoctorDTO> doctors = doctorRepository.findBySpecialty(specialty);

                if (doctors.isEmpty()) {
                    Map<String, Object> response = new HashMap<>();
                    response.put("status", "empty");
                    response.put("message", "No doctors found with specialty: " + specialty);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }

                return new ResponseEntity<>(doctors, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Unauthorized access", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
