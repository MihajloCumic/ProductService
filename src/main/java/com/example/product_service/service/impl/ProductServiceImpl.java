package com.example.product_service.service.impl;

import com.example.product_service.dto.product.ProductInDto;
import com.example.product_service.dto.product.ProductOutDto;
import com.example.product_service.entity.Product;
import com.example.product_service.exceptions.impl.ResourceNotFound;
import com.example.product_service.mapper.Mapper;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final Mapper<Product, ProductInDto, ProductOutDto> mapper;

    public ProductServiceImpl(ProductRepository productRepository, Mapper<Product, ProductInDto, ProductOutDto> mapper){
        this.productRepository = productRepository;
        this.mapper = mapper;
    }
    @Override
    public List<ProductOutDto> getAll() {
        List<Product> products = productRepository.findAll();
        return mapper.toDtoList(products);
    }

    @Override
    public ProductOutDto getById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("product:id")
        );
        return mapper.toDto(product);
    }
}
