package com.edwin.clinic.service;

import com.edwin.clinic.dto.DoctorDTO;
import com.edwin.clinic.entity.Doctor;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface DoctorService {

    ResponseEntity<String> addNewDoctor(DoctorDTO doctorDTO);

    ResponseEntity<List<DoctorDTO>>getAllDoctors();

}
