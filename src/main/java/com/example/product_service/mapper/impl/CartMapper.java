package com.example.product_service.mapper.impl;

import com.example.product_service.dto.cart.CartItemDto;
import com.example.product_service.dto.cart.CartOutDto;
import com.example.product_service.dto.product.ProductInDto;
import com.example.product_service.dto.product.ProductOutDto;
import com.example.product_service.entity.Cart;
import com.example.product_service.entity.CartItem;
import com.example.product_service.entity.Product;
import com.example.product_service.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class CartMapper implements Mapper<Cart, Void, CartOutDto> {
    private final Mapper<Product, ProductInDto, ProductOutDto> productMapper;

    public CartMapper(Mapper<Product, ProductInDto, ProductOutDto> productMapper){
        this.productMapper = productMapper;
    }

    @Override
    public Cart toEntity(Void dto) {
        return null;
    }

    @Override
    public CartOutDto toDto(Cart cart) {
        Set<CartItem> items = cart.getItems();
        List<CartItemDto> itemDtos = items.stream()
                .map(cartItem ->
                        new CartItemDto(productMapper.toDto(cartItem.getProduct()),
                                cartItem.getQuantity()))
                .toList();
        return new CartOutDto(itemDtos);
    }
}
