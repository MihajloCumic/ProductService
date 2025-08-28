package com.example.product_service.service;

import com.example.product_service.dto.cart.AddProductToCartDto;
import com.example.product_service.dto.cart.CartOutDto;
import com.example.product_service.dto.product.ProductInDto;

public interface CartService {
    CartOutDto getCart();
    CartOutDto addProductToCart(AddProductToCartDto productId);
}
