package com.edwin.clinic.restImpl;

import com.edwin.clinic.constants.ClinicConstants;
import com.edwin.clinic.dto.appointment.AppointmentRequestDTO;
import com.edwin.clinic.rest.AppointmentRest;
import com.edwin.clinic.service.AppointmentService;
import com.edwin.clinic.utils.ClinicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentRestImpl implements AppointmentRest {

    @Autowired
    AppointmentService appointmentService;

    @Override
    public ResponseEntity<String> registerAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        try{
            return appointmentService.registerAppointment(appointmentRequestDTO);
        }catch (Exception e){
         e.printStackTrace();
        }
        return ClinicUtils.getResponseEntity(ClinicConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
