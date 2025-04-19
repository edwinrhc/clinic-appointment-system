package com.edwin.clinic.rest;


import com.edwin.clinic.dto.appointment.AppointmentRequestDTO;
import com.edwin.clinic.dto.appointment.AppointmentResponseDTO;
import com.edwin.clinic.dto.appointment.AppointmentStatusUpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/appointment")
public interface AppointmentRest {

    @PostMapping(value = "/add")
    public ResponseEntity<String> registerAppointment(@RequestBody AppointmentRequestDTO appointmentRequestDTO);


    @GetMapping("/by-patient/{id}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByPatient(@PathVariable Integer id);

    @GetMapping("/by-doctor/{id}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByDoctor(@PathVariable Integer id);

    @PatchMapping("/update-status/{id}")
    public ResponseEntity<String> updateAppointmentStatus(@PathVariable Long id, @RequestBody AppointmentStatusUpdateDTO dto);

    @GetMapping("/by-status/{status}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentByStatus(@PathVariable String status);
}
