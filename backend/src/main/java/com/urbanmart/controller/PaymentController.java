package com.urbanmart.controller;

import com.razorpay.RazorpayException;
import com.urbanmart.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-order")
    public String createOrder(@jakarta.validation.Valid @RequestBody PaymentRequest request) throws RazorpayException {
        return paymentService.createOrder(request.getAmount());
    }

    @lombok.Data
    public static class PaymentRequest {
        @jakarta.validation.constraints.NotNull(message = "Amount is required")
        @jakarta.validation.constraints.Positive(message = "Amount must be greater than 0")
        private Double amount;
    }
}
