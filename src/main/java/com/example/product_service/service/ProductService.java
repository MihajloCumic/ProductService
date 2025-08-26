package com.example.product_service.service;

import com.example.product_service.dto.product.ProductOutDto;

import java.util.List;

public interface ProductService {
    List<ProductOutDto> getAll();
    ProductOutDto getById(Long id);
}
