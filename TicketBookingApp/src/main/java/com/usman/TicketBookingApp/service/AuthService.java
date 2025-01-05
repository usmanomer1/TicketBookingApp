package com.usman.TicketBookingApp.service;



import com.usman.TicketBookingApp.config.JwtUtils;
import com.usman.TicketBookingApp.dao.IUserRepo;
import com.usman.TicketBookingApp.model.User;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final IUserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthService(IUserRepo userRepo, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    // Register a new user
    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "User registered successfully!";
    }

    // Authenticate and generate JWT token
    public String login(String username, String password) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        if (authentication.isAuthenticated()) {
            return jwtUtils.generateToken(username);
        } else {
            throw new RuntimeException("Invalid credentials!");
        }
    }
}
