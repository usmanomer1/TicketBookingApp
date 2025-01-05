package com.usman.TicketBookingApp.restcontroller;

import com.usman.TicketBookingApp.model.Tourist;
import com.usman.TicketBookingApp.service.ITouristManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "https://ticketbookignapp-frontend.vercel.app")
public class TouristController {

	@Autowired
	private ITouristManagement bookingService;

	// ✅ Allow only users with "ROLE_USER" to create a booking
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<String> createBooking(@RequestBody Tourist tourist) {
		String msg = bookingService.registerTourist(tourist);
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}

	// ✅ Allow only users with "ROLE_ADMIN" to get all bookings
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<Tourist>> getAllBookings() {
		List<Tourist> bookings = bookingService.fetchAllTourists();
		return new ResponseEntity<>(bookings, HttpStatus.OK);
	}

	// ✅ Allow only authenticated users to get a booking by ID
	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Tourist> getBookingById(@PathVariable Integer id) {
		Tourist booking = bookingService.fetchTouristInfoById(id);
		return new ResponseEntity<>(booking, HttpStatus.OK);
	}
}
