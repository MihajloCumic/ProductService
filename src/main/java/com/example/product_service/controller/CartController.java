package com.example.product_service.controller;

import com.example.product_service.dto.cart.ItemInDto;
import com.example.product_service.dto.cart.CartOutDto;
import com.example.product_service.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Gets all items from the currently logged in users cart.")
    public ResponseEntity<CartOutDto> getCart(){
        CartOutDto cart = cartService.getCart();
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Operation(summary = "Add existing product as item to logged in users cart.",
            description = "All fields in body are mandatory. Quantity must be a positive integer.")
    public ResponseEntity<CartOutDto> addItemToCart(@Valid @RequestBody ItemInDto itemDto){
        CartOutDto cart = cartService.addItemToCart(itemDto);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/item")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Operation(summary = "Updated items quantity that is already in the users cart.",
            description = "All fields in body are mandatory. Quantity must be a positive integer.")
    public ResponseEntity<CartOutDto> updateItemQuantity(@Valid @RequestBody ItemInDto itemDto){
        CartOutDto cart = cartService.updateItemsQuantity(itemDto);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/item")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Operation(summary = "Deleted item from the cart if it exists in the logged in users cart.",
            description = "Quantity must be set to a positive integer (just to pass validation, this method does not use it).")
    public ResponseEntity<CartOutDto> deleteItem(@Valid @RequestBody ItemInDto itemDto){
        CartOutDto cart = cartService.deleteItem(itemDto);
        return ResponseEntity.ok(cart);
    }

}
