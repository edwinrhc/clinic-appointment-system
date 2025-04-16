package com.edwin.clinic.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    private Long id;

    @NotBlank(message = "The name is required")
    private String firstName;

    @NotBlank(message="The lastname is required")
    private String lastName;

    @NotBlank(message="The specialty is required")
    private String specialty;

    @Email(message="email is required")
    private String email;

    @NotBlank(message = "Phone is required")
    private String phone;


    public String getFullName() {
        return firstName + " " + lastName;
    }

}
