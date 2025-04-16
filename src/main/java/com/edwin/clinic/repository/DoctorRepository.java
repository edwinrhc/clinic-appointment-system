package com.edwin.clinic.repository;

import com.edwin.clinic.entity.Doctor;
import com.edwin.clinic.dto.DoctorDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository  extends JpaRepository<Doctor,Integer> {


    @Query("SELECT new com.edwin.clinic.dto.DoctorDTO(d.id, d.firstName, d.lastName, d.specialty, d.email, d.phone) FROM Doctor d")
    List<DoctorDTO> getAllDoctors();

    @Query("SELECT new com.edwin.clinic.dto.DoctorDTO(d.id, d.firstName, d.lastName, d.specialty, d.email, d.phone) " +
            "FROM Doctor d WHERE LOWER(d.specialty) = LOWER(:specialty)")
    List<DoctorDTO> findBySpecialty(String specialty);

    Doctor findByEmail(String email);


}
