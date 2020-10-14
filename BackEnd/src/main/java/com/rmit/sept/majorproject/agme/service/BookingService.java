package com.rmit.sept.majorproject.agme.service;

import com.rmit.sept.majorproject.agme.model.Booking;
import com.rmit.sept.majorproject.agme.model.BookingInfo;
import com.rmit.sept.majorproject.agme.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

	private static BookingRepository bookingRepository;
	private ServicesService servicesService;
	private ProviderService providerService;

	@Autowired
	public BookingService(BookingRepository bookingRepository, ServicesService servicesService, ProviderService providerService) {
		this.bookingRepository = bookingRepository;
		this.servicesService = servicesService;
		this.providerService = providerService;
	}

	public static List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	public boolean addBooking(Booking booking) {
		if (bookingIdExist(booking.getBooking_id())) {
			return false;
		}
		bookingRepository.save(booking);
		return true;
	}

	// get ongoing bookings for a specific user
	public List<BookingInfo> getOngoingBookings(Long user_id) {
		List<Booking> bookings = getAllBookings()
				.stream()
				.filter(booking -> booking.getUser_id().equals(user_id))
				.filter(booking -> booking.getStatus().equals("ongoing"))
				.collect(Collectors.toList());
		List<BookingInfo> bookingInfos = new ArrayList<>();
		for (Booking b : bookings) {
			bookingInfos.add(new BookingInfo(b.getBooking_id(), b.getUser_id(), b.getService_id(), servicesService.getServiceNameById(b.getService_id()), b.getProvider_id(), providerService.getProviderName(b.getProvider_id()), b.getStatus(), b.getBooking_date()));
		}
		return bookingInfos;
	}

	// get ongoing bookings for a specific service
	public List<Date> getOngoingBookingDatesOfService(Long service_id) {
		return getAllBookings()
				.stream()
				.filter(booking -> booking.getUser_id().equals(service_id))
				.filter(booking -> booking.getStatus().equals("ongoing"))
				.map(Booking::getBooking_date)
				.collect(Collectors.toList());
	}

	// get booking history (completed+cancelled) for a specific user
	public List<BookingInfo> getBookingHistory(Long user_id) {
		List<Booking> bookings = getAllBookings()
				.stream()
				.filter(booking -> booking.getUser_id().equals(user_id))
				.filter(booking -> !booking.getStatus().equals("ongoing"))
				.collect(Collectors.toList());

		List<BookingInfo> bookingInfos = new ArrayList<>();
		for (Booking b : bookings) {
			bookingInfos.add(new BookingInfo(b.getBooking_id(), b.getUser_id(), b.getService_id(), servicesService.getServiceNameById(b.getService_id()), b.getProvider_id(), providerService.getProviderName(b.getProvider_id()), b.getStatus(), b.getBooking_date()));
		}
		return bookingInfos;
	}

	// get booking history (completed only) for a specific user
	public List<Booking> getCompletedBookingHistory(Long user_id) {
		return getAllBookings()
				.stream()
				.filter(booking -> booking.getUser_id().equals(user_id))
				.filter(booking -> booking.getStatus().equals("completed"))
				.collect(Collectors.toList());
	}

	// set status of a specific booking (options: ongoing/completed/cancelled), returns true if changed
	public boolean setBookingStatus(Long booking_id, String status) {
		if(bookingIdExist(booking_id)) {
			getBookingById(booking_id).setStatus(status);
			return true;
		}
		return false;
	}

	// get status of a specific booking (options: ongoing/completed/cancelled), returns true if changed
	public String getBookingStatus(Long booking_id) {
		if(bookingIdExist(booking_id)) {
			return getBookingById(booking_id).getStatus();
		}
		return "N/A";
	}

//	public boolean isBookingCompleted(Long booking_id) {
//		if(bookingIdExist(booking_id)) {
//			if (getBookingById(booking_id).getStatus().equals("completed")) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public boolean isBookingOngoing(Long booking_id) {
//		if(bookingIdExist(booking_id)) {
//			if (getBookingById(booking_id).getStatus().equals("ongoing")) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public boolean isBookingCancelled(Long booking_id) {
//		if(bookingIdExist(booking_id)) {
//			if (getBookingById(booking_id).getStatus().equals("cancelled")) {
//				return true;
//			}
//		}
//		return false;
//	}

	public boolean bookingIdExist(Long booking_id) {
		return getAllBookings()
				.stream()
				.anyMatch(booking -> booking.getBooking_id().equals(booking_id));
	}

	public Booking getBookingById(Long booking_id) {
		return getAllBookings()
				.stream()
				.filter(booking -> booking.getBooking_id().equals(booking_id))
				.findFirst()
				.orElseThrow(() -> new IllegalStateException(String.format("Booking ID is not found", booking_id)));
	}
}
