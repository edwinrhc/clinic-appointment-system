package com.edwin.clinic.repository;

import com.edwin.clinic.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId And a.date = :date AND a.hora = :hora")
    Optional<Appointment> findDuplicateAppointment(@Param("doctorId")Integer doctorId,
                                                   @Param("date")LocalDate date,
                                                   @Param("hora") LocalTime hora);

    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientId AND a.date = :date AND a.hora = :hora")
    Optional<Appointment> findDuplicatePatientAppointment(@Param("patientId") Integer patientId,
                                                          @Param("date") LocalDate date,
                                                          @Param("hora") LocalTime hora);


    List<Appointment> findByPatientId(Integer patientId);

    List<Appointment> findByDoctorId(Integer doctorId);

    List<Appointment> findByStatusIgnoreCase(String status);

    @Query("SELECT a.status, COUNT(a) FROM Appointment a GROUP BY a.status")
    List<Object[]> countAppointmentsByStatus();

    List<Appointment> findByDate(LocalDate date);

    @Query("SELECT a.doctor.name, a.doctor.specialty.name, COUNT(a) " +
           "FROM Appointment a GROUP BY a.doctor.name, a.doctor.specialty.name " +
            "ORDER BY count(a) DESC")
    List<Object[]> getDoctorRanking();


}
