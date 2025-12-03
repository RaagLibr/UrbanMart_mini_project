package com.urbanmart.controller;

import com.urbanmart.entity.ContactMessage;
import com.urbanmart.service.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactMessageService service;

    @PostMapping
    public ResponseEntity<?> submitMessage(@jakarta.validation.Valid @RequestBody ContactMessage message) {
        service.saveMessage(message);
        return ResponseEntity.ok("Message sent successfully");
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ContactMessage>> getAllMessages() {
        return ResponseEntity.ok(service.getAllMessages());
    }

    @PutMapping("/{id}/review")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ContactMessage> toggleReviewStatus(@PathVariable Long id) {
        return ResponseEntity.ok(service.markAsReviewed(id));
    }
}
