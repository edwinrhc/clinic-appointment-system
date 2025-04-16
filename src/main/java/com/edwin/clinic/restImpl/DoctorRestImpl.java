package com.edwin.clinic.restImpl;

import com.edwin.clinic.constants.ClinicConstants;
import com.edwin.clinic.dto.DoctorDTO;
import com.edwin.clinic.rest.DoctorRest;
import com.edwin.clinic.service.DoctorService;
import com.edwin.clinic.utils.ClinicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class DoctorRestImpl implements DoctorRest {

    @Autowired
    DoctorService doctorService;

    @Override
    public ResponseEntity<String> add(DoctorDTO doctorDTO) {

        try{
            return doctorService.addNewDoctor(doctorDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ClinicUtils.getResponseEntity(ClinicConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<DoctorDTO>> get() {
       try{
           return doctorService.getAllDoctors();

       }catch (Exception ex){
           ex.printStackTrace();
          return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @Override
    public ResponseEntity<?> getDoctorsBySpecialty(String specialty) {
        try{
            return doctorService.getDoctorBySpecialty(specialty);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
