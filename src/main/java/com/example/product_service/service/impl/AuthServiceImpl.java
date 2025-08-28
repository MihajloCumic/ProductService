package com.example.product_service.service.impl;

import com.example.product_service.common.JwtUtil;
import com.example.product_service.common.Role;
import com.example.product_service.dto.user.UserOutDto;
import com.example.product_service.dto.user.auth.AuthResponseDto;
import com.example.product_service.dto.user.auth.LoginDto;
import com.example.product_service.dto.user.auth.RegistrationDto;
import com.example.product_service.entity.Cart;
import com.example.product_service.entity.User;
import com.example.product_service.mapper.Mapper;
import com.example.product_service.repository.UserRepository;
import com.example.product_service.service.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final Mapper<User, RegistrationDto, AuthResponseDto> mapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository,
                           Mapper<User, RegistrationDto, AuthResponseDto> mapper,
                           AuthenticationManager authenticationManager,
                           JwtUtil jwtUtil,
                           PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public AuthResponseDto register(RegistrationDto registrationDto) {
        if(userRepository.existsByEmail(registrationDto.details().email())){
            throw new RuntimeException("Email already exists.");
        }
        if(userRepository.existsByUsername(registrationDto.details().username())){
            throw new RuntimeException("Username already exists.");
        }

        User user = mapper.toEntity(registrationDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCart(new Cart());
        userRepository.save(user);

        return new AuthResponseDto(jwtUtil.generateToken(
                user.getEmail(),
                user.getRole()
        ));
    }

    @Override
    public AuthResponseDto login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String role = authentication.getAuthorities()
                .iterator()
                .next()
                .getAuthority();
        return new AuthResponseDto(
                jwtUtil.generateToken(loginDto.email(), Role.valueOf(role))
        );
    }
}
