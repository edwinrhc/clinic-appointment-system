package com.edwin.clinic.serviceImpl;

import com.edwin.clinic.constants.ClinicConstants;
import com.edwin.clinic.dto.appointment.AppointmentRequestDTO;
import com.edwin.clinic.dto.appointment.AppointmentResponseDTO;
import com.edwin.clinic.dto.appointment.AppointmentStatusUpdateDTO;
import com.edwin.clinic.entity.Appointment;
import com.edwin.clinic.entity.User;
import com.edwin.clinic.jwt.JwtFilter;
import com.edwin.clinic.repository.AppointmentRepository;
import com.edwin.clinic.repository.UserRepository;
import com.edwin.clinic.service.AppointmentService;
import com.edwin.clinic.utils.ClinicUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository  appointmentRepository;

    @Autowired
    UserRepository  userRepository;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> registerAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        log.info("Iniciando appointment: {}", appointmentRequestDTO);
        try{
            Optional<User> patient = userRepository.findById(appointmentRequestDTO.getPatientId());
            Optional<User> doctor = userRepository.findById(appointmentRequestDTO.getDoctorId());

            if (patient.isEmpty() || doctor.isEmpty()) {
                return ClinicUtils.getResponseEntity("Doctor or patient not found", HttpStatus.BAD_REQUEST);
            }
            LocalDate appointmentDate = LocalDate.parse(appointmentRequestDTO.getDate());
            LocalTime appointmentTime = LocalTime.parse(appointmentRequestDTO.getTime());

            Optional<Appointment> duplicate = appointmentRepository.findDuplicateAppointment(
                    appointmentRequestDTO.getDoctorId(),appointmentDate,appointmentTime);

            if(duplicate.isPresent()){
                return ClinicUtils.getResponseEntity("Doctor is not available at the selected date and time", HttpStatus.CONFLICT);
            }

            Optional<Appointment> patientConflict = appointmentRepository.findDuplicatePatientAppointment(
                    appointmentRequestDTO.getPatientId(),appointmentDate,appointmentTime);
            if(patientConflict.isPresent()){
                return ClinicUtils.getResponseEntity("You already have an appointment at this time", HttpStatus.CONFLICT);
            }

            Appointment appointment = toEntity(appointmentRequestDTO,patient.get(),doctor.get());
            appointmentRepository.save(appointment);
            return ClinicUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ClinicUtils.getResponseEntity(ClinicConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Appointment toEntity(AppointmentRequestDTO appointmentRequestDTO, User patient,User doctor) {

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDate(LocalDate.parse(appointmentRequestDTO.getDate()));
        appointment.setHora(LocalTime.parse(appointmentRequestDTO.getTime()));
        appointment.setStatus("PENDING");
        return appointment;
    }

    @Override
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByPatient(Integer patientId) {
        try{

            List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
            List<AppointmentResponseDTO> response = appointments.stream().map(a -> {
                return new AppointmentResponseDTO(
                        a.getDoctor().getName(),
                        a.getPatient().getName(),
                        a.getDoctor().getSpecialty().getName(),
                        a.getDate().toString(),
                        a.getHora().toString(),
                        a.getStatus()
                );
            }).toList();

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByDoctor(Integer doctorId) {
        try{
            List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId);
            List<AppointmentResponseDTO> response = appointments.stream().map(a -> new AppointmentResponseDTO(
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
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<String> updateAppointmentStatus(Long appointmentId, AppointmentStatusUpdateDTO appointmentStatusUpdateDTO) {
        try{
            Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
            if (optionalAppointment.isEmpty()) {
                return ClinicUtils.getResponseEntity("Appointment not found", HttpStatus.NOT_FOUND);
            }
            Appointment appointment = optionalAppointment.get();
            appointment.setStatus(appointmentStatusUpdateDTO.getStatus().toUpperCase());
            appointmentRepository.save(appointment);

            return ClinicUtils.getResponseEntity("Appointment status updated successfully", HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return ClinicUtils.getResponseEntity(ClinicConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByStatus(String status) {
       try {
           List<Appointment> appointments = appointmentRepository.findByStatusIgnoreCase(status);
           List<AppointmentResponseDTO> response = appointments.stream().map(a -> new AppointmentResponseDTO(
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
           return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
}
