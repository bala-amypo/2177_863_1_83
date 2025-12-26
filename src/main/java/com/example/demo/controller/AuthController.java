package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService userService,
                          JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public User register(@RequestBody AuthRequest request) {
        return userService.register(
                request.getEmail(),
                request.getPassword(),
                "ADMIN"
        );
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        User user = userService.login(
                request.getEmail(),
                request.getPassword()
        );

        String token = jwtTokenProvider.createToken(
                user.getEmail(),
                user.getRole(),
                user.getId()
        );

        return new AuthResponse(token);
    }
}
