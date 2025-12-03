package com.urbanmart.service;

import com.urbanmart.entity.Product;
import com.urbanmart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(String category, String search) {
        if (category != null && !category.isEmpty()) {
            return productRepository.findByCategory(category);
        }
        if (search != null && !search.isEmpty()) {
            return productRepository.findByNameContainingIgnoreCase(search);
        }
        return productRepository.findAll();
    }

    public Product getProductById(@org.springframework.lang.NonNull Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product createProduct(@org.springframework.lang.NonNull Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(@org.springframework.lang.NonNull Long id,
            @org.springframework.lang.NonNull Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setQuantity(productDetails.getQuantity());
        product.setDescription(productDetails.getDescription());
        product.setImage(productDetails.getImage());
        product.setCategory(productDetails.getCategory());
        return productRepository.save(product);
    }

    public void deleteProduct(@org.springframework.lang.NonNull Long id) {
        productRepository.deleteById(id);
    }
}
