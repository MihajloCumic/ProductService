package com.example.product_service.service;

import com.example.product_service.dto.cart.ItemInDto;
import com.example.product_service.dto.cart.CartOutDto;

public interface CartService {
    CartOutDto getCart();
    CartOutDto addItemToCart(ItemInDto itemDto);
    CartOutDto updateItemsQuantity(ItemInDto itemDto);
    CartOutDto deleteItem(ItemInDto itemInDto);
}
