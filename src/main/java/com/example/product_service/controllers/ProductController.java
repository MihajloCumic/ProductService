package com.example.product_service.controllers;

import com.example.product_service.common.ProductType;
import com.example.product_service.dto.product.ProductDetails;
import com.example.product_service.dto.product.ProductOutDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<ProductOutDto> getAllProducts(){
        var products = new ArrayList<ProductOutDto>();
        var p1 = new ProductOutDto(
                1L,
                new ProductDetails(
                        "product1",
                            ProductType.POWER_TOOL
                )
        );
        var p2 = new ProductOutDto(
                2L,
                new ProductDetails(
                        "product2",
                        ProductType.BATTERY
                )
        );
        products.add(p1);
        products.add(p2);
        return products;
    }

    @GetMapping("/{id}")
    public ProductOutDto getProductById(@PathVariable Long id){
        return new ProductOutDto(
                id,
                new ProductDetails(
                        "product1",
                        ProductType.POWER_TOOL
                )
        );
    }
}
