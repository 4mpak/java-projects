package com.example.gateway.controllers;

import com.example.gateway.dto.AuthRequest;
import com.example.gateway.models.User;
import com.example.gateway.models.Role;
import com.example.gateway.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "Register user", description = "Create a new user with ROLE_USER")
    @ApiResponse(responseCode = "200", description = "User created")
    @PostMapping("/register")
    public void register(@RequestBody AuthRequest authRequest) {
        User user = new User();
        user.setUsername(authRequest.getUsername());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
    }
}