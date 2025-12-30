package com.user.management.User_Management.service.impl;

import com.user.management.User_Management.dto.UserRequestDTO;
import com.user.management.User_Management.dto.UserResponseDTO;
import com.user.management.User_Management.entity.UserEntity;
import com.user.management.User_Management.repository.UserRepository;
import com.user.management.User_Management.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // Inyección de dependencias por constructor
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREAR
    @Override
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO request) {

        // 1. Validar regla de negocio: Email único
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        // 2. Convertir DTO a Entity
        UserEntity entity = new UserEntity();
        entity.setName(request.getName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail());
        entity.setPassword(request.getPassword()); // Pendiente: Encriptar
        entity.setActive(true);

        // 3. Guardar en BD
        UserEntity savedUser = userRepository.save(entity);

        // 4. Convertir Entity a ResponseDTO
        UserResponseDTO response = new UserResponseDTO();
        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setLastName(savedUser.getLastName());
        response.setEmail(savedUser.getEmail());
        response.setActive(savedUser.isActive());

        return response;
    }


    // LISTAR
    @Override
    @Transactional(readOnly = true) // Optimización: Le dice a la BD que solo vamos a leer, es más rápido.
    public List<UserResponseDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        // Mapeo usando Streams (Java 8+):
        // 1. Convertimos la lista a un flujo (stream)
        // 2. Por cada usuario (map), lo convertimos a DTO
        // 3. Recolectamos todo en una nueva lista
        return users.stream()
                .map(this::mapToDTO) // Usamos un método auxiliar para no repetir código
                .collect(Collectors.toList());
    }

    // LISTAR POR ID
    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long id) {
        // Buscamos por ID. Si no existe, lanzamos error.
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + id));

        return mapToDTO(user);
    }


    // Método privado para reutilizar la lógica de conversión Entity -> DTO
    // Esto es Código Limpio (DRY: Don't Repeat Yourself)
    private UserResponseDTO mapToDTO(UserEntity entity) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setActive(entity.isActive());
        return dto;
    }
}
