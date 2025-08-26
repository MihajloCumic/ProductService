package com.example.product_service.service.impl;

import com.example.product_service.dto.product.ProductOutDto;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public List<ProductOutDto> getAll() {
        return List.of();
    }

    @Override
    public ProductOutDto getById(Long id) {
        return null;
    }
}
