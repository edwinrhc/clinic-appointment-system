package com.edwin.clinic.restImpl;

import com.edwin.clinic.dto.appointment.AppointmentResponseDTO;
import com.edwin.clinic.dto.dashboard.DashboardStatusCountDTO;
import com.edwin.clinic.dto.dashboard.DoctorRankingDTO;
import com.edwin.clinic.rest.DashboardRest;
import com.edwin.clinic.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DashboardRestImpl implements DashboardRest {

    @Autowired
    DashboardService  dashboardService;

    @Override
    public ResponseEntity<List<DashboardStatusCountDTO>> getAppointmentStatusCounts() {
        try{
            return dashboardService.getAppointmentStatusCount();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<AppointmentResponseDTO>> getTodayAppointments() {
        try{
            return dashboardService.getTodayAppointments();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<DoctorRankingDTO>> getTopDoctors() {
        try{
        return dashboardService.getTopDoctor();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
