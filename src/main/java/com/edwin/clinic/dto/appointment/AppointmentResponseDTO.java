package com.edwin.clinic.dto.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppointmentResponseDTO {

    private String doctorName;
    private String patientName;
    private String specialty;
    private String date;
    private String time;
    private String status;

}
