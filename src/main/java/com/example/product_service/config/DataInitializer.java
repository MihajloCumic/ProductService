package com.example.product_service.config;

import com.example.product_service.common.ProductType;
import com.example.product_service.common.Role;
import com.example.product_service.entity.*;
import com.example.product_service.repository.CartItemRepository;
import com.example.product_service.repository.CartRepository;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(ProductRepository productRepository,
                           CartRepository cartRepository,
                           CartItemRepository cartItemRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder){
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        creatAdmin();
        createProducts();
    }

    private void creatAdmin(){
        User user = new User();

        user.setRole(Role.ADMIN);
        user.setEmail("admin@gmail.com");
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("123456789"));
        user.setActive(true);

        userRepository.save(user);
    }

    private void createProducts(){
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
        Cart cart = new Cart();
        List<CartItem> items = new ArrayList<>();
        cartRepository.save(cart);
        for(Product product: products){
            CartItemId id = new CartItemId();
            id.setCartId(cart.getId());
            id.setProductId(product.getId());

            CartItem item = new CartItem();
            item.setId(id);
            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(0);
            items.add((item));
        }
        cartItemRepository.saveAll(items);
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
