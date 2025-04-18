package com.edwin.clinic.service;

import com.edwin.clinic.dto.specialty.SpecialtyDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SpecialtyService {

    ResponseEntity<List<SpecialtyDTO>> getAllSpecialties();

    ResponseEntity<String> registerSpecialty(SpecialtyDTO specialtyDTO);
}
