package com.usman.TicketBookingApp.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;

    @ElementCollection
    private List<String> roles;  // Stores roles like "ROLE_USER", "ROLE_ADMIN"

    // Getters and Setters (Lombok @Data will generate them automatically)
}
