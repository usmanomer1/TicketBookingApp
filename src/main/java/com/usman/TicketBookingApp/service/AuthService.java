package com.usman.TicketBookingApp.service;



import com.usman.TicketBookingApp.config.JwtUtils;
import com.usman.TicketBookingApp.dao.IUserRepo;
import com.usman.TicketBookingApp.model.User;
import org.springframework.security.core.GrantedAuthority;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public String Login(String username, String password) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        if (authentication.isAuthenticated()) {
            // Pass both username and roles to the generateToken() method
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            return jwtUtils.generateToken(username, roles);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

}
