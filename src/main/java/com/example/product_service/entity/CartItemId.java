package com.example.product_service.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class CartItemId implements Serializable {
    private Long cartId;
    private Long productId;
}
