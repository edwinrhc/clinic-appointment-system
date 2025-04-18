package com.edwin.clinic.dto.specialty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialtyDTO {
    private Long id;

    @NotBlank(message = "The name is required")
    private String name;
}
