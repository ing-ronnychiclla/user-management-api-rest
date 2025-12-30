package com.user.management.User_Management.controller;

import com.user.management.User_Management.dto.UserRequestDTO;
import com.user.management.User_Management.dto.UserResponseDTO;
import com.user.management.User_Management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users") // URL base profesional con version
public class UserController {

    private final UserService userService;

    // Inyectamos la Interfaz (Spring buscará automáticamente la implementación 'UserServiceImpl')
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CREAR
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO request) {
        // @Valid activa las validaciones que pusimos en el DTO (NotBlank, Email, etc)
        // @RequestBody convierte el JSON que envía el usuario a objeto Java

        UserResponseDTO newUser = userService.createUser(request);

        // Devolvemos código 201 Created (Buenas prácticas REST)
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }



    // LISTAR
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users); // Retorna 200 OK
    }

    // LISTAR POR ID
    @GetMapping("/{id}") // La URL será /api/v1/users/1
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
