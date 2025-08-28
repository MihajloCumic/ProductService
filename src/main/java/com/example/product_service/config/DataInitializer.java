package com.example.product_service.config;

import com.example.product_service.common.ProductType;
import com.example.product_service.common.Role;
import com.example.product_service.entity.*;
import com.example.product_service.repository.CartRepository;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.repository.UserRepository;
import jakarta.transaction.Transactional;
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
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(ProductRepository productRepository,
                           CartRepository cartRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder){
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        User admin = creatAdmin();
        createProducts(admin);
    }

    private User creatAdmin(){
        Cart cart = new Cart();
        cartRepository.save(cart);
        User user = new User();

        user.setRole(Role.ADMIN);
        user.setEmail("admin@gmail.com");
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("123456789"));
        user.setActive(true);
        user.setCart(cart);

        return userRepository.save(user);
    }

    private void createProducts(User admin){
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

        Cart cart = admin.getCart();
        for(Product product: products){
            CartItemId id = new CartItemId();
            id.setCartId(cart.getId());
            id.setProductId(product.getId());

            CartItem item = new CartItem();
            item.setId(id);
            item.setProduct(product);
            item.setCart(cart);
            item.setQuantity(1);

            cart.getItems().add(item);
        }

        userRepository.save(admin);
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
