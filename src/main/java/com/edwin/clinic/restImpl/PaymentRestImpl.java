package com.edwin.clinic.restImpl;

import com.edwin.clinic.constants.ClinicConstants;
import com.edwin.clinic.dto.appointment.AppointmentPaymentDTO;
import com.edwin.clinic.rest.PaymentRest;
import com.edwin.clinic.service.PaymentService;
import com.edwin.clinic.utils.ClinicUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentRestImpl implements PaymentRest {

    private final PaymentService paymentService;


    @Override
    public ResponseEntity<String> processPayment(AppointmentPaymentDTO dto) {
        try{
            return paymentService.processPayment(dto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ClinicUtils.getResponseEntity(ClinicConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
