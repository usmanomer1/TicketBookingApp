package com.usman.TicketBookingApp.restcontroller;

import com.usman.TicketBookingApp.model.Tourist;
import com.usman.TicketBookingApp.service.ITouristManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "https://ticketbookignapp-frontend.vercel.app")
public class TouristController {

	@Autowired
	private ITouristManagement bookingService;

	// ✅ Create a new booking (Only for users with "ROLE_USER" or "ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<String> createBooking(@RequestBody Tourist tourist) {
		String msg = bookingService.registerTourist(tourist);
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}

	// ✅ Get all bookings (Only for users with "ROLE_ADMIN")
	@GetMapping
	public ResponseEntity<List<Tourist>> getAllBookings() {
		List<Tourist> bookings = bookingService.fetchAllTourists();
		return new ResponseEntity<>(bookings, HttpStatus.OK);
	}

	// ✅ Get a specific booking by ID (Only for authenticated users)
	@GetMapping("/{id}")
	public ResponseEntity<Tourist> getBookingById(@PathVariable Integer id) {
		Tourist booking = bookingService.fetchTouristInfoById(id);
		return new ResponseEntity<>(booking, HttpStatus.OK);
	}

	// ✅ Update a booking by ID (Only for users with "ROLE_ADMIN")
	@PutMapping("/{id}")
	public ResponseEntity<String> updateBooking(@PathVariable Integer id, @RequestBody Tourist tourist) {
		tourist.setTid(id);
		String msg = bookingService.updateTouristInfo(tourist);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	// ✅ Delete a booking by ID (Only for users with "ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBooking(@PathVariable Integer id) {
		String msg = bookingService.deleteTouristById(id);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
