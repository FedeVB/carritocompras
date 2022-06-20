package com.carrito.app.service.interfaces;

import com.carrito.app.domain.dto.UserDto;
import com.carrito.app.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    Optional<UserDto> findByEmailDto(String email);

    boolean existsById(Long id);

    boolean existsByEmail(String email);

    void deleteById(Long id);


}
