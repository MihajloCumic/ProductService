package com.example.product_service.dto.product;

import com.example.product_service.common.ProductType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDetails(
        @NotBlank String name,
        @NotNull ProductType type
        ) {
}
