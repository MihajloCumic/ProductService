package com.example.product_service.dto.cart;

import java.util.List;

public record CartOutDto(
        List<CartItemDto> items
) {
}
