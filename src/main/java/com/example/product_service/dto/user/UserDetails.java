package com.example.product_service.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDetails(
        @NotBlank @Email @Size(max = 254) String email,
        @NotBlank @Size(max = 50) String username
) {
}
