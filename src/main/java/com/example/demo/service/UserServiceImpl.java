package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
//import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    //private final JwtTokenProvider tokenProvider;

    public UserServiceImpl(UserRepository userRepository,
                           JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public User register(String email, String password, String role) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password); // (plain for now)
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        return tokenProvider.createToken(user.getEmail(), user.getRole());
    }
}
