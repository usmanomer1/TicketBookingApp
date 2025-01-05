package com.usman.TicketBookingApp.dao;

import com.usman.TicketBookingApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
