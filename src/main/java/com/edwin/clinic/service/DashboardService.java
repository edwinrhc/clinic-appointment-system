package com.edwin.clinic.service;

import com.edwin.clinic.dto.appointment.AppointmentResponseDTO;
import com.edwin.clinic.dto.dashboard.DashboardStatusCountDTO;
import com.edwin.clinic.dto.dashboard.DoctorRankingDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DashboardService {

    ResponseEntity<List<DashboardStatusCountDTO>> getAppointmentStatusCount();

    ResponseEntity<List<AppointmentResponseDTO>> getTodayAppointments();

    ResponseEntity<List<DoctorRankingDTO>> getTopDoctor();


}
