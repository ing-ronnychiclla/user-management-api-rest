package com.user.management.User_Management.service;

import com.user.management.User_Management.dto.UserRequestDTO;
import com.user.management.User_Management.dto.UserResponseDTO;

public interface UserService {

    // Solo la firma del método. Nada de lógica aquí.
    UserResponseDTO createUser(UserRequestDTO request);

    // Agrega esto
    java.util.List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(Long id);
}
