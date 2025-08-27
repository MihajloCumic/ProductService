package com.example.product_service.dto.product;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public record ProductOutDto(
        Long id,
        @JsonUnwrapped ProductDetails details
) {
}
