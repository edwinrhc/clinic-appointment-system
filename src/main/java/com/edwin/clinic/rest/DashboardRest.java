package com.edwin.clinic.rest;

import com.edwin.clinic.dto.appointment.AppointmentResponseDTO;
import com.edwin.clinic.dto.dashboard.DashboardStatusCountDTO;
import com.edwin.clinic.dto.dashboard.DoctorRankingDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/dashboard")
public interface DashboardRest {

    @GetMapping("/appointment-status-count")
    ResponseEntity<List<DashboardStatusCountDTO>> getAppointmentStatusCounts();

    @GetMapping("/today")
    ResponseEntity<List<AppointmentResponseDTO>> getTodayAppointments();

    @GetMapping("/top-doctors")
    ResponseEntity<List<DoctorRankingDTO>> getTopDoctors();

}
