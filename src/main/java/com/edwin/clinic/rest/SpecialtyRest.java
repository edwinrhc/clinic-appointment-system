package com.edwin.clinic.rest;

import com.edwin.clinic.dto.specialty.SpecialtyDTO;
import com.edwin.clinic.dto.user.UserListDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(path = "/specialty")
public interface SpecialtyRest {

    @PostMapping(path = "/add")
    public ResponseEntity<String> registerSpecialty(@RequestBody @Valid SpecialtyDTO specialtyDTO);

    @GetMapping("/get")
    public ResponseEntity<List<SpecialtyDTO>> getAllSpecialty();

}
