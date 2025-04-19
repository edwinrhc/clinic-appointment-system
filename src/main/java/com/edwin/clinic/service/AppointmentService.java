package com.edwin.clinic.service;

import com.edwin.clinic.dto.appointment.AppointmentPaymentDTO;
import com.edwin.clinic.dto.appointment.AppointmentRequestDTO;
import com.edwin.clinic.dto.appointment.AppointmentResponseDTO;
import com.edwin.clinic.dto.appointment.AppointmentStatusUpdateDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppointmentService {

    ResponseEntity<String> registerAppointment(AppointmentRequestDTO appointmentRequestDTO);

    ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByPatient(Integer patientId);

    ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByDoctor(Integer doctorId);

    ResponseEntity<String>updateAppointmentStatus(Long appointmentId, AppointmentStatusUpdateDTO appointmentStatusUpdateDTO);

    ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByStatus(String status);

    ResponseEntity<String> proccessPayment(AppointmentPaymentDTO dto);
}
