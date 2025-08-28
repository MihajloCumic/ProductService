package com.example.product_service.service.impl;

import com.example.product_service.dto.cart.CartOutDto;
import com.example.product_service.entity.Cart;
import com.example.product_service.entity.User;
import com.example.product_service.mapper.Mapper;
import com.example.product_service.repository.CartRepository;
import com.example.product_service.service.CartService;
import com.example.product_service.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final Mapper<Cart, Void, CartOutDto> mapper;

    public CartServiceImpl(CartRepository cartRepository,
                           UserService userService,
                           Mapper<Cart, Void, CartOutDto> mapper){
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.mapper = mapper;
    }


    @Override
    public CartOutDto getCart() {
        User user = userService.loadCurrentUser();
        return mapper.toDto(user.getCart());
    }

}
