package com.example.product_service.repository;

import com.example.product_service.entity.CartItem;
import com.example.product_service.entity.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {
}
