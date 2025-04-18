package com.edwin.clinic.restImpl;

import com.edwin.clinic.constants.ClinicConstants;
import com.edwin.clinic.dto.doctor.DoctorRequestDTO;
import com.edwin.clinic.rest.DoctorRest;
import com.edwin.clinic.service.DoctorService;
import com.edwin.clinic.utils.ClinicUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorRestImpl implements DoctorRest {

    @Autowired
    DoctorService doctorService;

    @Override
    public ResponseEntity<String> registerDoctor(@RequestBody @Valid DoctorRequestDTO doctorRequestDTO) {
        try {
            return doctorService.registerDoctor(doctorRequestDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ClinicUtils.getResponseEntity(ClinicConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
