package com.usman.TicketBookingApp.service;

import java.util.List;

import com.usman.TicketBookingApp.model.Tourist;

public interface ITouristManagement 
{
     public String registerTourist(Tourist tourist);
     public List<Tourist> fetchAllTourists();
     public Tourist fetchTouristInfoById(Integer id);
     public String updateTouristInfo(Tourist tourist);
     public String updateTouristInfoById(Integer id, Double budget);
     public String deleteTouristById(Integer id);
}
