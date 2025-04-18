package com.edwin.clinic.serviceImpl;

import com.edwin.clinic.constants.ClinicConstants;
import com.edwin.clinic.dto.specialty.SpecialtyDTO;
import com.edwin.clinic.entity.Specialty;
import com.edwin.clinic.jwt.JwtFilter;
import com.edwin.clinic.jwt.JwtUtil;
import com.edwin.clinic.repository.SpecialtyRepository;
import com.edwin.clinic.service.SpecialtyService;
import com.edwin.clinic.utils.ClinicUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    @Autowired
    SpecialtyRepository specialtyRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<List<SpecialtyDTO>> getAllSpecialties() {
      try{
        return new ResponseEntity<>(specialtyRepository.listSpecialty(), HttpStatus.OK);
      }catch (Exception e){
          e.printStackTrace();
      }
      return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<String> registerSpecialty(SpecialtyDTO specialtyDTO) {
        log.info("Trying to register a specialty: {}", specialtyDTO);
        try{
            if(jwtFilter.isAdmin()){
                    Specialty specialty = toEntity(specialtyDTO);
                    specialtyRepository.save(specialty);
                    return ClinicUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);
            }else{
                return ClinicUtils.getResponseEntity(ClinicConstants.UNAUTHORIZED_ACCESS,HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ClinicUtils.getResponseEntity(ClinicConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Specialty toEntity(SpecialtyDTO specialtyDTO) {
        Specialty specialty = new Specialty();
        specialty.setName(specialtyDTO.getName());
        return specialty;
    }
}
