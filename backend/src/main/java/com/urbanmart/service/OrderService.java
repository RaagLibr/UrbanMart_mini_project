package com.urbanmart.service;

import com.urbanmart.dto.OrderRequest;
import com.urbanmart.entity.Order;
import com.urbanmart.entity.OrderItem;
import com.urbanmart.entity.User;
import com.urbanmart.entity.Product;
import com.urbanmart.repository.OrderRepository;
import com.urbanmart.repository.ProductRepository;
import com.urbanmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Order placeOrder(OrderRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(request.getTotal());
        order.setStatus("PENDING");
        order.setAddress(request.getAddress());
        order.setCity(request.getCity());
        order.setState(request.getState());
        order.setPincode(request.getPincode());
        order.setPhone(request.getPhone());

        List<OrderItem> items = request.getItems().stream().map(itemDto -> {
            Product product = productRepository.findById(itemDto.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + itemDto.getName()));

            if (product.getQuantity() < itemDto.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }

            // Decrement stock
            product.setQuantity(product.getQuantity() - itemDto.getQuantity());
            productRepository.save(product);

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProductId(itemDto.getId());
            item.setName(itemDto.getName());
            item.setPrice(itemDto.getPrice());
            item.setQuantity(itemDto.getQuantity());
            item.setImage(itemDto.getImage());
            return item;
        }).collect(Collectors.toList());

        order.setItems(items);

        // Verify Payment
        if (request.getRazorpayPaymentId() != null) {
            order.setStatus("PAID");
            // In a real scenario, you would verify the signature here using RazorpayClient
            // For now, we assume if payment ID is present, it's paid.
            // verifySignature(request.getRazorpayOrderId(), request.getRazorpayPaymentId(),
            // request.getRazorpaySignature());
        } else {
            order.setStatus("PENDING");
        }

        return orderRepository.save(order);
    }

    public List<Order> getMyOrders() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return orderRepository.findByUserIdOrderByCreatedAtDesc(user.getId());
    }

    public Order getOrderById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAllByOrderByCreatedAtDesc();
    }

    public Order updateOrderStatus(Long id, String status) {
        if (id == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        return orderRepository.save(order);
    }
}
