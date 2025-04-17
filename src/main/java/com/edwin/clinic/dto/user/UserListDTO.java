package com.edwin.clinic.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListDTO {
    private Integer id;
    private String name;
    private String contactNumber;
    private String email;
    private String role;
    private String status;

}
