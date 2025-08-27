package com.example.product_service.service;

import com.example.product_service.dto.user.UserOutDto;
import com.example.product_service.dto.user.auth.RegistrationDto;

public interface AuthService {
    UserOutDto register(RegistrationDto registrationDto);
}
