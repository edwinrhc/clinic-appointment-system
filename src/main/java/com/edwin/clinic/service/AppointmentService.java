package com.edwin.clinic.service;

import com.edwin.clinic.dto.appointment.AppointmentRequestDTO;
import com.edwin.clinic.dto.appointment.AppointmentResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppointmentService {

    ResponseEntity<String> registerAppointment(AppointmentRequestDTO appointmentRequestDTO);

    ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByPatient(Integer patientId);

    ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByDoctor(Integer doctorId);

}
