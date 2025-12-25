package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String role) {
        userService.register(email, password, role);
        return "User registered";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {
        return userService.login(email, password);
    }
}
