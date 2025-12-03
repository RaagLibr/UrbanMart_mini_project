package com.urbanmart.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private List<OrderItemDto> items;

    @jakarta.validation.constraints.NotBlank(message = "Address is required")
    private String address;

    @jakarta.validation.constraints.NotBlank(message = "City is required")
    private String city;

    @jakarta.validation.constraints.NotBlank(message = "State is required")
    private String state;

    @jakarta.validation.constraints.NotBlank(message = "Pincode is required")
    @jakarta.validation.constraints.Pattern(regexp = "^\\d{6}$", message = "Invalid Pincode")
    private String pincode;

    @jakarta.validation.constraints.NotBlank(message = "Phone number is required")
    @jakarta.validation.constraints.Pattern(regexp = "^\\d{10}$", message = "Invalid Phone Number")
    private String phone;

    private Double total;
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;

    @Data
    public static class OrderItemDto {
        private Long id; // Product ID
        private String name;
        private Double price;
        private Integer quantity;
        private String image;
    }
}
