package com.edwin.clinic.rest;

import com.edwin.clinic.dto.appointment.AppointmentPaymentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/payment")
public interface PaymentRest {

    @PostMapping("/process")
    ResponseEntity<String> processPayment(@RequestBody AppointmentPaymentDTO dto);
}
