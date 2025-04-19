package com.edwin.clinic.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoctorRankingDTO {
    private String doctorName;
    private String specialty;
    private Long totalAppointments;
}
