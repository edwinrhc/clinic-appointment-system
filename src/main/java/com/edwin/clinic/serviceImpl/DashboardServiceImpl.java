package com.edwin.clinic.serviceImpl;

import com.edwin.clinic.dto.appointment.AppointmentResponseDTO;
import com.edwin.clinic.dto.dashboard.DashboardStatusCountDTO;
import com.edwin.clinic.dto.dashboard.DoctorRankingDTO;
import com.edwin.clinic.entity.Appointment;
import com.edwin.clinic.repository.AppointmentRepository;
import com.edwin.clinic.service.DashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public ResponseEntity<List<DashboardStatusCountDTO>> getAppointmentStatusCount() {
        log.info("Inside getAppointmentStatusCount");
        try{
            List<Object[]> results = appointmentRepository.countAppointmentsByStatus();
            List<DashboardStatusCountDTO> response = results.stream()
                    //TODO: Task
                    .map(r -> new DashboardStatusCountDTO((String) r[0],(Long) r[1]))
                    .toList();
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<List<AppointmentResponseDTO>> getTodayAppointments() {
        log.info("Inside getTodayAppointments");
        try{
            LocalDate today = LocalDate.now();
            List<Appointment> appointments = appointmentRepository.findByDate(today);
            List<AppointmentResponseDTO> response = appointments.stream()
                    .map(a -> new AppointmentResponseDTO(
                            a.getDoctor().getName(),
                            a.getPatient().getName(),
                            a.getDoctor().getSpecialty().getName(),
                            a.getDate().toString(),
                            a.getHora().toString(),
                            a.getStatus()
                    )).toList();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<DoctorRankingDTO>> getTopDoctor() {
        log.info("Inside getTopDoctor");
        try{
            List<Object[]> results = appointmentRepository.getDoctorRanking();
            List<DoctorRankingDTO> response = results.stream()
                    .map(r-> new DoctorRankingDTO((String) r[0],(String) r[1], (Long) r[2]))
                    .toList();
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
