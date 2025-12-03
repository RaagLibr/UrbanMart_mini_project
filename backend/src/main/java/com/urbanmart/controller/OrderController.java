package com.urbanmart.controller;

import com.urbanmart.dto.OrderRequest;
import com.urbanmart.entity.Order;
import com.urbanmart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order placeOrder(@jakarta.validation.Valid @RequestBody OrderRequest request) {
        return orderService.placeOrder(request);
    }

    @GetMapping("/my")
    public List<Order> getMyOrders() {
        return orderService.getMyOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/admin/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/admin/{id}/status")
    public Order updateOrderStatus(@PathVariable Long id, @RequestBody String status) {
        // Remove quotes if present (simple handling)
        String cleanStatus = status.replace("\"", "");
        return orderService.updateOrderStatus(id, cleanStatus);
    }
}
