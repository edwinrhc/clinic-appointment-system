package com.edwin.clinic.dto.user;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStatusDTO {
    private Integer id;

    @NotBlank(message = "Status is required")
    private String status;
}