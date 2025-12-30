package com.user.management.User_Management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Table(name = "users") // Buena práctica: Nombres de tablas en plural y minúsculas
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // No permite nulos
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true) // Email unico en la BD
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp // Spring pone la fecha actual automáticamente al crear
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp // Se actualiza sola cada vez que modificas el usuario
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private boolean isActive = true; // Por defecto nace activo
}
