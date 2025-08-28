package com.example.product_service.mapper.impl;

import com.example.product_service.common.Role;
import com.example.product_service.dto.user.auth.AuthResponseDto;
import com.example.product_service.dto.user.auth.RegistrationDto;
import com.example.product_service.entity.User;
import com.example.product_service.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper implements Mapper<User, RegistrationDto, AuthResponseDto> {
    @Override
    public User toEntity(RegistrationDto dto) {
        User user = new User();
        user.setRole(Role.USER);
        user.setUsername(dto.details().username());
        user.setEmail(dto.details().email());
        user.setPassword(dto.password());
        user.setActive(true);
        return user;
    }

    @Override
    public AuthResponseDto toDto(User user) {
        return null;
    }
}
