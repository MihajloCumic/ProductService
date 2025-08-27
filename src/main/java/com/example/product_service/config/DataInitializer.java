package com.example.product_service.config;

import com.example.product_service.common.ProductType;
import com.example.product_service.entity.Product;
import com.example.product_service.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private static final String SEED_DIR = "seed/";
    private static final String FILE_EXT = ".txt";
    private final ProductRepository productRepository;

    public DataInitializer(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        List<Product> products = new ArrayList<>();
        for(ProductType type: ProductType.values()){
            List<String> productNames = loadProductNames(type);
            for(String name: productNames){
                Product product = new Product();
                product.setName(name);
                product.setType(type);

                products.add(product);
            }
        }
        productRepository.saveAll(products);
    }

    private List<String> loadProductNames(ProductType type){
        String fileName = SEED_DIR + type.name().toLowerCase() + "s" + FILE_EXT;
        ClassPathResource resource = new ClassPathResource(fileName);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream())
        )){
            List<String> productNames = new ArrayList<>();
            String line;
            while((line = reader.readLine()) != null){
                productNames.add(line);
            }
            return productNames;

        } catch (IOException e) {
            throw new IllegalStateException("Failed to load seed file: " + fileName);
        }
    }
}
