package com.urbanmart.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.urbanmart.entity.Product;
import com.urbanmart.entity.User;
import com.urbanmart.repository.ProductRepository;
import com.urbanmart.repository.UserRepository;

@Component
public class DataSeeder implements CommandLineRunner {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        public void run(String... args) throws Exception {
                seedUsers();
                seedProducts();
        }

        private void seedUsers() {
                if (userRepository.count() == 0) {
                        User admin = new User();
                        admin.setName("Admin User");
                        admin.setEmail("admin@urbanmart.com");
                        admin.setPassword(passwordEncoder.encode("admin123"));
                        admin.setRole(User.Role.ADMIN);
                        userRepository.save(admin);

                        User user = new User();
                        user.setName("John Doe");
                        user.setEmail("john@example.com");
                        user.setPassword(passwordEncoder.encode("password"));
                        user.setRole(User.Role.USER);
                        userRepository.save(user);

                        System.out.println("Users seeded!");
                }
        }

        @SuppressWarnings("null")
        private void seedProducts() {
                if (productRepository.count() == 0) {
                        List<Product> products = Arrays.asList(
                                        // Fruits
                                        new Product(null, "Fresh Apples", 120.0, 50, "Crisp and sweet red apples",
                                                        "/apple_image.png",
                                                        "fruits"),
                                        new Product(null, "Bananas", 60.0, 100, "Fresh yellow bananas",
                                                        "/banana_image_1.png", "fruits"),
                                        new Product(null, "Oranges", 80.0, 80, "Juicy oranges rich in Vitamin C",
                                                        "/orange_image.png",
                                                        "fruits"),
                                        new Product(null, "Grapes", 90.0, 60, "Fresh green grapes",
                                                        "/grapes_image_1.png", "fruits"),
                                        new Product(null, "Mangoes", 150.0, 40, "Sweet seasonal mangoes",
                                                        "/mango_image_1.png", "fruits"),

                                        // Vegetables
                                        new Product(null, "Carrots", 40.0, 60, "Fresh organic carrots",
                                                        "/carrot_image.png", "vegetables"),
                                        new Product(null, "Cabbage", 30.0, 40, "Fresh green cabbage", "/cabage.png",
                                                        "vegetables"),
                                        new Product(null, "Potatoes", 30.0, 200, "Farm fresh potatoes",
                                                        "/potato_image_1.png",
                                                        "vegetables"),
                                        new Product(null, "Spinach", 25.0, 50, "Fresh leafy spinach",
                                                        "/spinach_image_1.png", "vegetables"),
                                        new Product(null, "Onions", 35.0, 100, "Red onions", "/onion_image_1.png",
                                                        "vegetables"),
                                        new Product(null, "Tomatoes", 40.0, 80, "Fresh red tomatoes",
                                                        "/tomato_image.png", "vegetables"),

                                        // Dairy
                                        new Product(null, "Amul Milk", 50.0, 40, "Fresh Amul milk 1L",
                                                        "/amul_milk_image.png", "dairy"),
                                        new Product(null, "Paneer", 250.0, 20, "Fresh cottage cheese",
                                                        "/paneer_image.png", "dairy"),
                                        new Product(null, "Ice Cream", 150.0, 30, "Vanilla ice cream tub",
                                                        "/icecream.png", "dairy"),

                                        // Bakery & Others
                                        new Product(null, "Brown Bread", 45.0, 25, "Freshly baked brown bread",
                                                        "/brown_bread_image.png",
                                                        "bakery"),
                                        new Product(null, "Chocolate Cake", 450.0, 10, "Rich chocolate truffle cake",
                                                        "/cake.png",
                                                        "bakery"),
                                        new Product(null, "Basmati Rice", 120.0, 50, "Premium basmati rice 1kg",
                                                        "/basmati_rice_image.png",
                                                        "bakery"),
                                        new Product(null, "Brown Rice", 140.0, 40, "Healthy brown rice 1kg",
                                                        "/brown_rice_image.png",
                                                        "bakery"));

                        productRepository.saveAll(products);
                        System.out.println("Products seeded!");
                }
        }
}
