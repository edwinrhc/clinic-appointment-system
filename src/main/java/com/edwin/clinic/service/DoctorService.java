package com.edwin.clinic.service;

import com.edwin.clinic.dto.doctor.DoctorRequestDTO;
import org.springframework.http.ResponseEntity;

public interface DoctorService {


    ResponseEntity<String> registerDoctor(DoctorRequestDTO doctorRequestDTO);

}
