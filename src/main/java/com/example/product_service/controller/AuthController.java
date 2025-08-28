package com.example.product_service.controller;

import com.example.product_service.dto.user.auth.AuthResponseDto;
import com.example.product_service.dto.user.auth.LoginDto;
import com.example.product_service.dto.user.auth.RegistrationDto;
import com.example.product_service.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    @Operation(summary = "Registers a new user.",
            description = "All fields in body are mandatory. Password must be at least 8 characters long. Returns JWT.")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody RegistrationDto registrationDto){
        AuthResponseDto authResponseDto = authService.register(registrationDto);
        return ResponseEntity.ok(authResponseDto);
    }

    @PostMapping("/login")
    @Operation(summary = "Login existing user",
            description = "All fields in body are mandatory. Password must be at least 8 characters long. Returns JWT.")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginDto loginDto){
        AuthResponseDto authResponseDto = authService.login(loginDto);
        return ResponseEntity.ok(authResponseDto);
    }


}
