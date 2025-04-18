package com.edwin.clinic.repository;

import com.edwin.clinic.dto.specialty.SpecialtyDTO;
import com.edwin.clinic.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {

    @Query("SELECT new com.edwin.clinic.dto.specialty.SpecialtyDTO(s.id,s.name) FROM Specialty s")
    List<SpecialtyDTO> listSpecialty();
}
