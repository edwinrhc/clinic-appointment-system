package com.edwin.clinic.rest;


import com.edwin.clinic.dto.DoctorDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/doctor")
public interface DoctorRest {

    @PostMapping(path = "/add")
    public ResponseEntity<String> add(@RequestBody @Valid DoctorDTO doctorDTO);


    @GetMapping(path = "/get")
    public ResponseEntity<List<DoctorDTO>> get();


}
