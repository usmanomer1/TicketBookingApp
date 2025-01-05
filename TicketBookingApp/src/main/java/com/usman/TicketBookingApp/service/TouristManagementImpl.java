package com.usman.TicketBookingApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usman.TicketBookingApp.dao.ITouristRepo;
import com.usman.TicketBookingApp.exception.TouristNotFoundException;
import com.usman.TicketBookingApp.model.Tourist;

@Service
public class TouristManagementImpl implements ITouristManagement 
{

	@Autowired
	private ITouristRepo repo;
	
	@Override
	public String registerTourist(Tourist tourist) 
	{
		Integer id=repo.save(tourist).getTid();
		return "Tourist Info saved  with an ID " + id;
	}

	@Override
	public List<Tourist> fetchAllTourists() {
		
		List<Tourist> list= repo.findAll();
		
//		Collections.shuffle(list);
//		list.
		return list;
	}

	@Override
	public Tourist fetchTouristInfoById(Integer id) 
	{
//		Optional<Tourist> optional = repo.findById(id);
//		Tourist tourist = optional.get();
//		return tourist;
		return repo.findById(id).
				orElseThrow(()->new TouristNotFoundException("Tourist with given id "+ id+ " is Not Found"));
	}

	@Override
	public String updateTouristInfo(Tourist tourist) 
	{
		Integer id=tourist.getTid();
		Optional<Tourist> optional = repo.findById(id);
		if(optional.isPresent())
			{
			       repo.save(tourist);
			       return "Tourist info with id "+ id + " is updated" ;
			}
		else
		{
			throw new TouristNotFoundException("Tourist with given info is nout found to update");
		}
		
	}

	@Override
	public String updateTouristInfoById(Integer id, Double budget) 
	{
		Optional<Tourist> optional = repo.findById(id);
		if(optional.isPresent())
		{
			Tourist tourist = optional.get();
			tourist.setPrice(budget);
			repo.save(tourist);
			return "Tourist info with id "+ id + " is updated" ;
			
		}
		else
		{
			throw new TouristNotFoundException("Tourist with given info is nout found to update");

		}
		
	}

	@Override
	public String deleteTouristById(Integer id) 
	{
		Optional<Tourist> optional = repo.findById(id);
		if(optional.isPresent())
		{
//			Tourist tourist = optional.get();
//			repo.delete(tourist);
			repo.deleteById(id);
			return "Tourist Info with id "+ id+" is deleted";
		}
		else
		{
			throw new TouristNotFoundException("Tourist with given info is not found to delete data");

		}
		
	}

}
