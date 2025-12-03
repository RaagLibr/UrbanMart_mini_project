package com.urbanmart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @jakarta.validation.constraints.NotBlank(message = "Product name is required")
    private String name;

    @jakarta.validation.constraints.NotNull(message = "Price is required")
    @jakarta.validation.constraints.Positive(message = "Price must be greater than 0")
    private Double price;

    @jakarta.validation.constraints.NotNull(message = "Quantity is required")
    @jakarta.validation.constraints.Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    @Column(length = 1000)
    @jakarta.validation.constraints.NotBlank(message = "Description is required")
    private String description;

    @jakarta.validation.constraints.NotBlank(message = "Image URL is required")
    private String image;

    @jakarta.validation.constraints.NotBlank(message = "Category is required")
    private String category;
}
