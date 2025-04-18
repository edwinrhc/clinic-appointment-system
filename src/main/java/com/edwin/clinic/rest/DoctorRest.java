package com.edwin.clinic.rest;


import com.edwin.clinic.dto.doctor.DoctorRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/doctor")
public interface DoctorRest {

    @PostMapping(path = "/add")
    public ResponseEntity<String> registerDoctor(DoctorRequestDTO doctorRequestDTO);

}
