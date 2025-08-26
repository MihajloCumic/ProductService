package com.example.product_service.dto.product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record ProductInDto(
        @Valid @NotNull ProductDetails details
) {
}
