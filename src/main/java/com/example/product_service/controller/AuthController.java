package com.example.product_service.controller;

import com.example.product_service.dto.user.UserDetails;
import com.example.product_service.dto.user.UserOutDto;
import com.example.product_service.dto.user.auth.LoginDto;
import com.example.product_service.dto.user.auth.RegistrationDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register")
    public UserOutDto register(@RequestBody RegistrationDto registrationDto){
        System.out.println(registrationDto);
        return new UserOutDto(
                new UserDetails(
                        "email1@gmail.com",
                        "username1"
                )
        );
    }

    @PostMapping("/login")
    public UserOutDto login(@RequestBody LoginDto loginDto){
        System.out.println(loginDto);
        return new UserOutDto(
                new UserDetails(
                        "email1@gmail.com",
                        "username1"
                )
        );
    }


}
