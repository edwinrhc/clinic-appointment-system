package com.edwin.clinic.restImpl;

import com.edwin.clinic.constants.ClinicConstants;
import com.edwin.clinic.dto.specialty.SpecialtyDTO;
import com.edwin.clinic.rest.SpecialtyRest;
import com.edwin.clinic.service.SpecialtyService;
import com.edwin.clinic.utils.ClinicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SpecialtyRestImpl implements SpecialtyRest {

    @Autowired
    SpecialtyService specialtyService;

    @Override
    public ResponseEntity<String> registerSpecialty(SpecialtyDTO specialtyDTO) {
       try{
            return specialtyService.registerSpecialty(specialtyDTO);
       }catch (Exception e){
           e.printStackTrace();
       }
       return ClinicUtils.getResponseEntity(ClinicConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<SpecialtyDTO>> getAllSpecialty() {
       try{
            return specialtyService.getAllSpecialties();
       }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List<SpecialtyDTO>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
}
