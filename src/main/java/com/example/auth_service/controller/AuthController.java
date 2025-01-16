package com.example.auth_service.controller;

import com.example.auth_service.models.LoginRequest;
import com.example.auth_service.models.RegistrationRequest;
import com.example.auth_service.models.UserResponse;
import com.example.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired AuthService authService;

    @GetMapping("/")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @PostMapping("/register")
    public void register(@RequestBody RegistrationRequest request) {
        authService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        return username + " " + password;
    }

    @PostMapping("/logout")
    public String logout() {
        return "Logout";
    }

    @GetMapping("/getUser")
    public UserResponse getUser(@RequestParam(value="email") String email) {
        return authService.getUser(email);
    }

    @PostMapping("/changePassword")
    public String changePassword() {
        return "changePassword";
    }
}
