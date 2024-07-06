package com.usman.TicketBookingApp.dao;

import com.usman.TicketBookingApp.model.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository	
public interface ITouristRepo extends JpaRepository<Tourist, Integer>
{

}
