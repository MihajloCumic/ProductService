package com.example.product_service.controller;

import com.example.product_service.dto.cart.AddProductToCartDto;
import com.example.product_service.dto.cart.CartOutDto;
import com.example.product_service.dto.product.ProductInDto;
import com.example.product_service.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<CartOutDto> getCart(){
        CartOutDto cart = cartService.getCart();
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<CartOutDto> addProductToCart(@Valid @RequestBody AddProductToCartDto productId){
        CartOutDto cart = cartService.addProductToCart(productId);
        return ResponseEntity.ok(cart);
    }

}
