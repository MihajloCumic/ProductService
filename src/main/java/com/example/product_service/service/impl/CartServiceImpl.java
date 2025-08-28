package com.example.product_service.service.impl;

import com.example.product_service.dto.cart.ItemInDto;
import com.example.product_service.dto.cart.CartOutDto;
import com.example.product_service.entity.*;
import com.example.product_service.exceptions.impl.ResourceAlreadyExists;
import com.example.product_service.exceptions.impl.ResourceNotFound;
import com.example.product_service.mapper.Mapper;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.CartService;
import com.example.product_service.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private final ProductRepository productRepository;
    private final UserService userService;
    private final Mapper<Cart, Void, CartOutDto> cartMapper;

    public CartServiceImpl(ProductRepository productRepository,
                           UserService userService,
                           Mapper<Cart, Void, CartOutDto> cartMapper){
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
    public CartOutDto addItemToCart(ItemInDto itemDto) {
        User user = userService.loadCurrentUser();

        Product product = productRepository.findById(itemDto.productId()).orElseThrow(
                () -> new ResourceNotFound("product:id")
        );

        Cart cart = user.getCart();

        CartItemId id = new CartItemId();
        id.setProductId(product.getId());
        id.setCartId(cart.getId());

        CartItem cartItem = new CartItem();
        cartItem.setId(id);
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(itemDto.quantity());

        if(user.getCart().getItems().add(cartItem)){
            userService.saveUser(user);
            return cartMapper.toDto(cart);
        }
        throw new ResourceAlreadyExists("cart:product");
    }

    @Override
    public CartOutDto updateItemsQuantity(ItemInDto itemDto) {
        User user = userService.loadCurrentUser();
        Cart cart = user.getCart();

        CartItem cartItem = findCartItem(cart, itemDto.productId());
        cartItem.setQuantity(itemDto.quantity());

        userService.saveUser(user);
        return cartMapper.toDto(cart);
    }

    @Override
    public CartOutDto deleteItem(ItemInDto itemInDto) {
        User user = userService.loadCurrentUser();
        Cart cart = user.getCart();

        CartItem cartItem = findCartItem(user.getCart(), itemInDto.productId());

        if(user.getCart().getItems().remove(cartItem)){
            userService.saveUser(user);
            return cartMapper.toDto(cart);
        }
        throw new ResourceNotFound("item:product");
    }

    private CartItem findCartItem(Cart cart, long productId){

        return cart.getItems().stream()
                .filter(item -> item.getId().getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFound("cart:product"));
    }

}
