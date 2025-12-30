package com.user.management.User_Management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;

    @NotBlank(message = "El email es oblitorio")
    @Email(message = "El formato del email no es vaido")
    private String email;

    @NotBlank(message = "La contrasena es obligatoria")
    @Size(min = 6, message = "la contrasena debe tener al menos 6 caracteres")
    private String password;
}
