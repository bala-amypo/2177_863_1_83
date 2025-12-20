package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // ✅ Constructor Injection (MANDATORY as per instructions)
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(String email, String password, String role) {

        if (userRepository.existsByEmail(email)) {
            throw new DuplicateResourceException("unique");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password); // ⚠ plain text (security skipped)
        user.setRole(role);

        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("invalid password");
        }

        return user;
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }
}
