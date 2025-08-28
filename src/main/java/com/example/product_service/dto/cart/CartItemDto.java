package com.example.product_service.dto.cart;

import com.example.product_service.dto.product.ProductOutDto;

public record CartItemDto(
        ProductOutDto product,
        int quantity
) {
}
