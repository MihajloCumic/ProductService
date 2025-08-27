package com.example.product_service.service.impl;

import com.example.product_service.dto.user.UserOutDto;
import com.example.product_service.dto.user.auth.RegistrationDto;
import com.example.product_service.repository.UserRepository;
import com.example.product_service.service.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserOutDto register(RegistrationDto registrationDto) {
        if(userRepository.existsByEmail(registrationDto.details().email())){
            throw new RuntimeException("Email already exists.");
        }
        if(userRepository.existsByUsername(registrationDto.details().username())){
            throw new RuntimeException("Username already exists.");
        }



    }
}
