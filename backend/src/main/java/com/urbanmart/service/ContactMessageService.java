package com.urbanmart.service;

import com.urbanmart.entity.ContactMessage;
import com.urbanmart.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactMessageService {

    @Autowired
    private ContactMessageRepository repository;

    public ContactMessage saveMessage(@org.springframework.lang.NonNull ContactMessage message) {
        return repository.save(message);
    }

    public List<ContactMessage> getAllMessages() {
        return repository.findAllByOrderByCreatedAtDesc();
    }

    public ContactMessage markAsReviewed(@org.springframework.lang.NonNull Long id) {
        ContactMessage msg = repository.findById(id).orElseThrow(() -> new RuntimeException("Message not found"));
        msg.setReviewed(true);
        return repository.save(msg);
    }
}
