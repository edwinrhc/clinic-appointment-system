package com.edwin.clinic.dto.doctor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DoctorRequestDTO {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "contactNumber is required")
    private String contactNumber;

    @Email(message = "email is required")
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    @NotNull(message = "Specialty is required")
    private Long specialtyId;
}
