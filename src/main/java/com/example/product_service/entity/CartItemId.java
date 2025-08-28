package com.example.product_service.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class CartItemId implements Serializable {
    @EqualsAndHashCode.Include
    private Long cartId;
    @EqualsAndHashCode.Include
    private Long productId;
}
