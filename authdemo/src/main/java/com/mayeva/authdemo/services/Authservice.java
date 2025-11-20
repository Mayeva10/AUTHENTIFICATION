package com.mayeva.authdemo.services;

import com.mayeva.authdemo.entities.User;
import com.mayeva.authdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Authservice {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User register(User user) {
        // Vérifie si l'email existe déjà
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }

        // Hash du mot de passe
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Sauvegarde l’utilisateur
        return userRepository.save(user);
    }

    public boolean login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        return passwordEncoder.matches(password, user.getPassword());
    }
}
