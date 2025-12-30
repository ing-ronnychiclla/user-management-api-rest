package com.user.management.User_Management.repository;

import com.user.management.User_Management.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // ¡Aquí viene la magia de Spring Data JPA!
    // No necesitamos escribir SQL. Solo declaramos el método y Spring lo "inventa".

    // SELECT * FROM users WHERE email = ?
    Optional<UserEntity> findByEmail(String email);

    // Método para verificar si existe (útil para validaciones antes de crear)
    boolean existsByEmail(String email);
}
