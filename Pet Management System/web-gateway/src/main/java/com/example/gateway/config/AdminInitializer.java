package com.example.gateway.config;

import com.example.gateway.models.Role;
import com.example.gateway.models.User;
import com.example.gateway.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("4mpak").isEmpty()) {
            User admin = new User();
            admin.setUsername("4mpak");
            admin.setPassword(passwordEncoder.encode("12345678"));
            admin.setRole(Role.ROLE_ADMIN);
            userRepository.save(admin);
        }
    }
}