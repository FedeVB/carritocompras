package com.carrito.app.controllers;

import com.carrito.app.domain.entity.Authority;
import com.carrito.app.domain.entity.User;
import com.carrito.app.service.interfaces.UserService;
import com.carrito.app.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        User user = userService.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user) {
        user.setAuthorities(Arrays.asList(new Authority(3L,"ROLE_USER")));
        User newUser = userService.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody User user) {
        if (user.getId() == null) {
            throw new UserNotFoundException("User not found exception");
        }
        User updateUser = userService.save(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
