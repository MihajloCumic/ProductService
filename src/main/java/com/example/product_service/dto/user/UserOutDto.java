package com.example.product_service.dto.user;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public record UserOutDto(
        @JsonUnwrapped UserDetails details
) {
}
