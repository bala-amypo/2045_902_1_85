package com.example.demo.controller;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // ---------------- REGISTER ----------------
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {

        // check if email already exists
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(
                passwordEncoder.encode(registerRequest.getPassword())
        );
        user.setRole(
                registerRequest.getRole() == null
                        ? "ANALYST"
                        : registerRequest.getRole()
        );

        userRepository.save(user);

        return "User registered successfully";
    }

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {

        // 🔴 THIS IS THE LINE YOU ASKED ABOUT
        User user = userRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        // check password
        if (!passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtTokenProvider.generateToken(
                loginRequest.getEmail(),
                user.getRole()
        );

        return new AuthResponse(
                token,
                user.getEmail(),
                user.getRole()
        );
    }
}
