package com.edwin.clinic.serviceImpl;

import com.edwin.clinic.constants.ClinicConstants;
import com.edwin.clinic.dto.appointment.AppointmentPaymentDTO;
import com.edwin.clinic.entity.Appointment;
import com.edwin.clinic.entity.Payment;
import com.edwin.clinic.repository.AppointmentRepository;
import com.edwin.clinic.repository.PaymentRepository;
import com.edwin.clinic.service.PaymentService;
import com.edwin.clinic.utils.ClinicUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    AppointmentRepository  appointmentRepository;

    @Autowired
    PaymentRepository paymentRepository;


    @Override
    public ResponseEntity<String> processPayment(AppointmentPaymentDTO dto) {
       log.info("Inside : {}", dto);
        try{
            Optional<Appointment> optional = appointmentRepository.findById(dto.getAppointmentId());

            if(optional.isEmpty()){
                return ClinicUtils.getResponseEntity("Appointment not found", HttpStatus.NOT_FOUND);
            }
            Appointment appointment = optional.get();

            if("PAID".equalsIgnoreCase(appointment.getPaymentStatus())){
                return ClinicUtils.getResponseEntity("Appointment is Paid", HttpStatus.BAD_REQUEST);
            }
            Payment payment = toEntity(dto, appointment);

            paymentRepository.save(payment);

            //Actualiza estado de la cita
            appointment.setPaymentStatus("PAID");
            appointment.setStatus("CONFIRMED");
            appointmentRepository.save(appointment);

            return ClinicUtils.getResponseEntity("Payment processed successfully", HttpStatus.OK);

       }catch (Exception e){
           e.printStackTrace();
           return ClinicUtils.getResponseEntity(ClinicConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    private Payment toEntity(AppointmentPaymentDTO dto, Appointment appointment) {
        Payment payment = new Payment();
        payment.setAppointment(appointment);
        payment.setAmount(dto.getAmount());
        payment.setMethod(dto.getPaymentMethod());
        payment.setStatus("PAID");
        payment.setPaymentDate(LocalDateTime.now());
        return payment;
    }
}
