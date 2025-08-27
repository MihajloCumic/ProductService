package com.example.product_service.mapper.impl;

import com.example.product_service.dto.product.ProductDetails;
import com.example.product_service.dto.product.ProductInDto;
import com.example.product_service.dto.product.ProductOutDto;
import com.example.product_service.entity.Product;
import com.example.product_service.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<Product, ProductInDto, ProductOutDto> {
    @Override
    public Product toEntity(ProductInDto dto) {
        Product product = new Product();
        product.setName(dto.details().name());
        product.setType(dto.details().type());
        return product;
    }

    @Override
    public ProductOutDto toDto(Product product) {
        return new ProductOutDto(
                product.getId(),
                new ProductDetails(
                        product.getName(),
                        product.getType()
                )
        );
    }
}
