package com.edwin.clinic.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer id;

    @NotBlank(message = "The name is required")
    private String name;

    @NotBlank(message = "contactNumber is required")
    private String contactNumber;

    @NotBlank(message = "password is required")
    private String password;

    //TODO: Falta validar eje  no validad si es texto
    @Email(message="email is required")
    private String email;



    private String status;

}
