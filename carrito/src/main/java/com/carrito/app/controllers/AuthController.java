package com.carrito.app.controllers;

import com.carrito.app.domain.dto.LoginDto;
import com.carrito.app.domain.entity.Authority;
import com.carrito.app.domain.entity.User;
import com.carrito.app.security.jwt.JwtProvider;
import com.carrito.app.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "http://localhost:4200",methods = {RequestMethod.POST})
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping(value = "/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody User user) throws Exception {
        Map<String, Object> response = new HashMap<>();
        User newUser;

        if (userService.existsByEmail(user.getEmail())) {
            throw new Exception("User exists");
        }

        user.setAuthorities(Arrays.asList(new Authority(3L, "ROLE_USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser = userService.save(user);
        response.put("newUser", newUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginDto loginDto) {
        Map<String, Object> response = new HashMap<>();

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);

        response.put("token", "Bearer " + jwt);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
