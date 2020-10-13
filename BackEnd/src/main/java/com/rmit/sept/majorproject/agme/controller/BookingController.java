package com.rmit.sept.majorproject.agme.controller;

import com.rmit.sept.majorproject.agme.model.Booking;
import com.rmit.sept.majorproject.agme.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin("*") // accepting post/get from any sources, change if needed
public class BookingController {

	private BookingService bookingService;

	@Autowired
	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	// creating a booking
	@PostMapping("create-booking")
	public ResponseEntity<?> createBooking(@Valid @RequestBody Booking booking, BindingResult result) {
		if (result.hasErrors()){
			return new ResponseEntity<String>("Invalid Booking Object", HttpStatus.BAD_REQUEST);
		}
		return bookingService.addBooking(booking) ?
				new ResponseEntity<>(booking, HttpStatus.CREATED) : new ResponseEntity<String>("Failed to create booking", HttpStatus.BAD_REQUEST);
	}

	// get booked dates of a specific service
	@GetMapping("booked-dates/{serviceId}")
	public Iterable<Date> getBookedDatesOfService(@PathVariable("serviceId")  Long service_id) {
		return bookingService.getOngoingBookingDatesOfService(service_id);
	}

	// get ongoing bookings of a specific user
	@GetMapping("ongoing-bookings/{personId}")
	public Iterable<Booking> getPersonalOngoingBookings(@PathVariable("personId") Long person_id) {
		return bookingService.getOngoingBookings(person_id);
	}

	// get booking history (both completed and cancelled) of a specific user
	@GetMapping("booking-history/{personId}")
	public Iterable<Booking> getPersonalBookingHistory(@PathVariable("personId") Long person_id) {
		return bookingService.getBookingHistory(person_id);
	}

	// get completed bookings (only completed) of a specific user
	@GetMapping("completed-booking-history/{personId}")
	public Iterable<Booking> getPersonalCompletedBookingHistory(@PathVariable("personId") Long person_id) {
		return bookingService.getCompletedBookingHistory(person_id);
	}

	@GetMapping("booking-status/{bookingId}")
	public ResponseEntity<?> getBookingStatus(@PathVariable("bookingId") Long booking_id) {
		return new ResponseEntity<>(bookingService.getBookingStatus(booking_id), HttpStatus.OK);
	}

	// set the status of a booking, please only choose the following options: ongoing, completed, cancelled
	@PostMapping("update-booking-status/{bookingId}")
	public ResponseEntity<?> updateBookingStatus(@PathVariable("bookingId") Long booking_id, @Valid @RequestBody String booking_status, BindingResult result) {
		if (result.hasErrors()){
			return new ResponseEntity<>("Invalid status given", HttpStatus.BAD_REQUEST);
		}
		return bookingService.setBookingStatus(booking_id, booking_status) ?
				new ResponseEntity<>(HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
