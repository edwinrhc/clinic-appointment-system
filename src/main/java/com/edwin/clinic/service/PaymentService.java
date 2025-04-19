package com.edwin.clinic.service;

import com.edwin.clinic.dto.appointment.AppointmentPaymentDTO;
import org.springframework.http.ResponseEntity;

public interface PaymentService {

    ResponseEntity<String> processPayment(AppointmentPaymentDTO dto);

}
