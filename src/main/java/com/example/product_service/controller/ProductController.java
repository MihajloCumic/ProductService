package com.example.product_service.controller;

import com.example.product_service.dto.product.ProductOutDto;
import com.example.product_service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Operation(summary = "Gets all products.")
    public ResponseEntity<List<ProductOutDto>> getAllProducts(){
        List<ProductOutDto> products = productService.getAll();
        return ResponseEntity.ok(products);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Operation(summary = "Get product by id.", description = "Id must be equal or greater than 0.")
    public ResponseEntity<ProductOutDto> getProductById(@PathVariable @Min(0) Long id){
        ProductOutDto product = productService.getById(id);
        return ResponseEntity.ok(product);
    }
}
