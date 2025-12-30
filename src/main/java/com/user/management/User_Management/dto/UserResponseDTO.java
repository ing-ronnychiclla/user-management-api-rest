package com.user.management.User_Management.dto;

import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private boolean isActive;

    // Opcional: Podrías devolver las fechas si el frontend las necesita
    // private LocalDateTime createdAt;
}
