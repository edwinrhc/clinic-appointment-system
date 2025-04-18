package com.edwin.clinic.service;

import com.edwin.clinic.dto.appointment.AppointmentRequestDTO;
import org.springframework.http.ResponseEntity;

public interface AppointmentService {
    ResponseEntity<String> registerAppointment(AppointmentRequestDTO appointmentRequestDTO);


}
