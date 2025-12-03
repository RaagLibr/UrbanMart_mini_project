package com.urbanmart.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "contact_messages")
public class ContactMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @jakarta.validation.constraints.NotBlank(message = "Name is required")
    private String name;

    @jakarta.validation.constraints.NotBlank(message = "Email is required")
    @jakarta.validation.constraints.Email(message = "Invalid email format")
    private String email;

    @Column(columnDefinition = "TEXT")
    @jakarta.validation.constraints.NotBlank(message = "Message is required")
    private String message;

    private boolean reviewed = false;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
