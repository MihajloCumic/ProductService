package com.example.product_service.service;

import com.example.product_service.dto.user.auth.AuthResponseDto;
import com.example.product_service.dto.user.auth.LoginDto;
import com.example.product_service.dto.user.auth.RegistrationDto;

public interface AuthService {
    AuthResponseDto register(RegistrationDto registrationDto);
    AuthResponseDto login(LoginDto loginDto);
}
