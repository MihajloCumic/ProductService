package com.example.product_service.service.impl;

import com.example.product_service.dto.cart.AddProductToCartDto;
import com.example.product_service.dto.cart.CartOutDto;
import com.example.product_service.dto.product.ProductInDto;
import com.example.product_service.dto.product.ProductOutDto;
import com.example.product_service.entity.*;
import com.example.product_service.mapper.Mapper;
import com.example.product_service.repository.CartRepository;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.CartService;
import com.example.product_service.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final Mapper<Cart, Void, CartOutDto> cartMapper;

    public CartServiceImpl(CartRepository cartRepository,
                           ProductRepository productRepository,
                           UserService userService,
                           Mapper<Cart, Void, CartOutDto> cartMapper){
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userService = userService;
        this.cartMapper = cartMapper;
    }
    @Override
    public CartOutDto getCart() {
        User user = userService.loadCurrentUser();
        return cartMapper.toDto(user.getCart());
    }

    @Override
    public CartOutDto addProductToCart(AddProductToCartDto productId) {
        User user = userService.loadCurrentUser();

        Product product = productRepository.findById(productId.id()).orElseThrow(
                () -> new RuntimeException("Product not found.")
        );

        Cart cart = user.getCart();

        CartItemId id = new CartItemId();
        id.setProductId(product.getId());
        id.setCartId(cart.getId());

        CartItem cartItem = new CartItem();
        cartItem.setId(id);
        cartItem.setCart(cart);
        cartItem.setProduct(product);

        if(user.getCart().getItems().add(cartItem)){
            userService.saveUser(user);
            return cartMapper.toDto(cart);
        }
        throw new RuntimeException("Product is already in the cart.");
    }

}
