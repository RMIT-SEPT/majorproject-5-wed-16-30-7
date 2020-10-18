package com.rmit.sept.majorproject.agme.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmit.sept.majorproject.agme.model.Booking;
import com.rmit.sept.majorproject.agme.model.BookingInfo;
import com.rmit.sept.majorproject.agme.model.Person;
import com.rmit.sept.majorproject.agme.service.BookingService;
import com.rmit.sept.majorproject.agme.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.util.DateUtil.now;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class BookingControllerTest {

	private MockMvc mockMvc;

	@Mock
	private BookingService bookingService;

	@InjectMocks
	private BookingController bookingController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
	}

	@Test
	void getPersonalOngoingBookingsTest() throws Exception {
		Long userId = (long) 1;
		Long serviceId = (long) 1;
		Long providerId = (long) 1;
		BookingInfo bookingInfo1 = new BookingInfo((long) 1, userId, serviceId, "service1" , providerId, "provider1", "ongoing", now());
		BookingInfo bookingInfo2 = new BookingInfo((long) 2, userId, serviceId, "service2" , providerId, "provider2", "ongoing", now());


		when(bookingService.getOngoingBookings(userId)).thenReturn(
				Stream.of(
						bookingInfo1,
						bookingInfo2
				).collect(Collectors.toList())
		);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/booking/ongoing-bookings/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void getPersonalBookingHistoryTest() throws Exception {
		Long userId = (long) 1;
		Long serviceId = (long) 1;
		Long providerId = (long) 1;
		BookingInfo bookingInfo1 = new BookingInfo((long) 1, userId, serviceId, "service1" , providerId, "provider1", "completed", now());
		BookingInfo bookingInfo2 = new BookingInfo((long) 2, userId, serviceId, "service2" , providerId, "provider2", "cancelled", now());

		when(bookingService.getBookingHistory(userId)).thenReturn(
				Stream.of(
						bookingInfo1,
						bookingInfo2
				).collect(Collectors.toList())
		);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/booking/booking-history/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void getPersonalCompletedBookingHistoryTest() throws Exception {
		Long userId = (long) 1;
		Long serviceId = (long) 1;
		Long providerId = (long) 1;
		BookingInfo bookingInfo1 = new BookingInfo((long) 1, userId, serviceId, "service1" , providerId, "provider1", "completed", now());
		BookingInfo bookingInfo2 = new BookingInfo((long) 2, userId, serviceId, "service2" , providerId, "provider2", "completed", now());

		when(bookingService.getBookingHistory(userId)).thenReturn(
				Stream.of(
						bookingInfo1,
						bookingInfo2
				).collect(Collectors.toList())
		);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/booking/completed-booking-history/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void getBookingStatusTest() throws Exception {
		Long serviceId = (long) 1;
		Long providerId = (long) 1;
		Booking booking1 = new Booking((long) 1, (long) 1, serviceId, providerId, "completed", now());
		Booking booking2 = new Booking((long) 2, (long) 2, serviceId, providerId, "ongoing", now());

		when(bookingService.getBookingStatus(booking1.getBooking_id())).thenReturn(booking1.getStatus());
		when(bookingService.getBookingStatus(booking2.getBooking_id())).thenReturn(booking2.getStatus());

		mockMvc.perform(MockMvcRequestBuilders.get("/api/booking/booking-status/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("completed"));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/booking/booking-status/2"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("ongoing"));
	}

	@Test
	void updateBookingStatusTest() throws Exception {
		Long serviceId = (long) 1;
		Long providerId = (long) 1;
		Booking booking1 = new Booking((long) 1, (long) 1, serviceId, providerId, "ongoing", now());
		String newStatus = "cancelled";

		when(bookingService.setBookingStatus(booking1.getBooking_id(), newStatus)).thenReturn(true);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/booking/update-booking-status/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(newStatus))
				.andExpect(MockMvcResultMatchers.status().isAccepted());
	}
}