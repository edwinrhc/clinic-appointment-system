package com.edwin.clinic.dto.appointment;

import lombok.Data;

@Data
public class AppointmentRequestDTO {

    private Integer patientId;
    private Integer doctorId;
    private String date; // formato "yyy-MM-dd"
    private String time; // formato "HH:mm"


}
