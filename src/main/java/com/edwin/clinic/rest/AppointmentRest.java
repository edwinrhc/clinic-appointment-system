package com.edwin.clinic.rest;


import com.edwin.clinic.dto.appointment.AppointmentRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/appointment")
public interface AppointmentRest {

    @PostMapping(value = "/add")
    public ResponseEntity<String> registerAppointment(@RequestBody AppointmentRequestDTO appointmentRequestDTO);
}
