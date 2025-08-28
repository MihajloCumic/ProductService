package com.example.product_service.dto.cart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemInDto(
        @NotNull long productId,
        @Positive int quantity
) {
}
