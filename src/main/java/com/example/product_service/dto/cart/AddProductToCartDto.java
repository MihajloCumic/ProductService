package com.example.product_service.dto.cart;

import jakarta.validation.constraints.NotNull;

public record AddProductToCartDto(
        @NotNull long id
) {
}
