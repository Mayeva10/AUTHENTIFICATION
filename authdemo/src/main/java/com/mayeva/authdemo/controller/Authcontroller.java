package com.mayeva.authdemo.controller;

import com.mayeva.authdemo.entities.User;
import com.mayeva.authdemo.services.Authservice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class Authcontroller {

    private final Authservice authService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        boolean success = authService.login(user.getEmail(), user.getPassword());
        return success ? "Connexion réussie " : "Échec de connexion ";
    }
}
