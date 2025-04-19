package com.edwin.clinic.dto.appointment;

import lombok.Data;

@Data
public class AppointmentPaymentDTO {

    private Long appointmentId;
    private String paymentMethod; // Visa, MasterCArd enfin..
    private Double amount;
}
