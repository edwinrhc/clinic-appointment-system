package com.edwin.clinic.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardStatusCountDTO {
    private String status;
    private Long count;
}
