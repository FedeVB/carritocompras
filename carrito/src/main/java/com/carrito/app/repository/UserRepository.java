package com.carrito.app.repository;

import com.carrito.app.domain.dto.UserDto;
import com.carrito.app.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT new com.carrito.app.domain.dto.UserDto(u.firstName,u.lastName,u.email) FROM User u WHERE u.id = :idUser")
    Optional<UserDto> findByIdDto(Long idUser);

    @Query(value = "SELECT u FROM User u WHERE u.email = :emailUser")
    Optional<User> findByUserEmail(String emailUser);

    boolean existsByEmail(String email);
}
