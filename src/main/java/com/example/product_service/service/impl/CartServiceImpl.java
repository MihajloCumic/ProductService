package com.example.product_service.service.impl;

import com.example.product_service.repository.CartRepository;
import com.example.product_service.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserDetailsServiceImpl userDetailsService;

    public CartServiceImpl(CartRepository cartRepository, UserDetailsServiceImpl userDetailsService){
        this.cartRepository = cartRepository;
        this.userDetailsService = userDetailsService;
    }


}
