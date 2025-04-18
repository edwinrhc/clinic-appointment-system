package com.edwin.clinic.serviceImpl;


import com.edwin.clinic.constants.ClinicConstants;
import com.edwin.clinic.dto.doctor.DoctorRequestDTO;
import com.edwin.clinic.entity.Specialty;
import com.edwin.clinic.entity.User;
import com.edwin.clinic.jwt.JwtFilter;
import com.edwin.clinic.jwt.JwtUtil;
import com.edwin.clinic.repository.DoctorRepository;
import com.edwin.clinic.repository.SpecialtyRepository;
import com.edwin.clinic.repository.UserRepository;
import com.edwin.clinic.service.DoctorService;
import com.edwin.clinic.utils.ClinicUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SpecialtyRepository specialtyRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public ResponseEntity<String> registerDoctor(DoctorRequestDTO doctorRequestDTO) {
        log.info("Trying to register Doctor: {}", doctorRequestDTO );
       try{
           User validEmail = userRepository.findByEmail(doctorRequestDTO.getEmail());
           if(Objects.isNull(validEmail)){
               if(jwtFilter.isAdmin()){
                   User doctor = toEntity(doctorRequestDTO);
                   userRepository.save(doctor);
                   return ClinicUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);
               }
               else {
                   return ClinicUtils.getResponseEntity(ClinicConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
               }
           }else{
               return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
           }
       }catch (Exception e){
           e.printStackTrace();
       }
        return ClinicUtils.getResponseEntity(ClinicConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private User toEntity(DoctorRequestDTO doctorRequestDTO){
        Optional<Specialty> optionalSpecialty = specialtyRepository.findById(doctorRequestDTO.getSpecialtyId());
        User user = new User();
        user.setName(doctorRequestDTO.getName());
        user.setEmail(doctorRequestDTO.getEmail());
        user.setContactNumber( doctorRequestDTO.getContactNumber() );
        user.setPassword(passwordEncoder.encode(doctorRequestDTO.getPassword()));
        user.setRole("doctor");
        user.setStatus("true");
        user.setSpecialty(optionalSpecialty.get());
        return  user;
    }
}
