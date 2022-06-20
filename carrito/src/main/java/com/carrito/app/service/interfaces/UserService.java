package com.carrito.app.service.interfaces;

import com.carrito.app.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    boolean existsById(Long id);

    boolean existsByEmail(String email);

    void deleteById(Long id);


}
