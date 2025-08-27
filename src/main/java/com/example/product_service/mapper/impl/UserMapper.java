package com.example.product_service.mapper.impl;

import com.example.product_service.dto.user.UserDetails;
import com.example.product_service.dto.user.UserOutDto;
import com.example.product_service.dto.user.auth.RegistrationDto;
import com.example.product_service.entity.User;
import com.example.product_service.mapper.Mapper;

public class UserMapper implements Mapper<User, RegistrationDto, UserOutDto> {
    @Override
    public User toEntity(RegistrationDto dto) {
        User user = new User();
        user.setUsername(dto.details().username());
        user.setEmail(dto.details().email());
        user.setPassword(dto.password());
        return user;
    }

    @Override
    public UserOutDto toDto(User user) {
        return new UserOutDto(
                new UserDetails(
                        user.getEmail(),
                        user.getUsername()
                )
        );
    }
}
