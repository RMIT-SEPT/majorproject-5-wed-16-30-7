package com.rmit.sept.majorproject.agme.service;

import com.rmit.sept.majorproject.agme.model.Booking;
import com.rmit.sept.majorproject.agme.repositories.BookingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.util.DateUtil.now;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceTest {
	@Autowired
	private BookingService bookingService;

	@MockBean
	private BookingRepository bookingRepository;

	@Test
	public void getAllBookingsTest() {
		when(bookingRepository.findAll()).thenReturn(
				Stream.of(
						new Booking((long) 1, (long) 1, "Booking 1", "ongoing", now()),
						new Booking((long) 2, (long) 1, "Booking 2", "ongoing", now())
				).collect(Collectors.toList())
		);
		assertEquals(2, bookingService.getAllBookings().size());
	}

	@Test
	public void getOngoingBookingsTest() {
		when(bookingRepository.findAll()).thenReturn(
				Stream.of(
						new Booking((long) 1, (long) 1, "Booking 1", "ongoing", now()),
						new Booking((long) 2, (long) 1, "Booking 2", "completed", now()),
						new Booking((long) 3, (long) 1, "Booking 3", "ongoing", now()),
						new Booking((long) 4, (long) 1, "Booking 4", "cancelled", now())
				).collect(Collectors.toList())
		);
		// 2 ongoing bookings
		assertEquals(2, bookingService.getOngoingBookings((long) 1).size());
	}

	@Test
	public void getBookingHistoryTest() {
		when(bookingRepository.findAll()).thenReturn(
				Stream.of(
						new Booking((long) 1, (long) 1, "Booking 1", "ongoing", now()),
						new Booking((long) 2, (long) 1, "Booking 2", "completed", now()),
						new Booking((long) 3, (long) 1, "Booking 3", "ongoing", now()),
						new Booking((long) 4, (long) 1, "Booking 4", "cancelled", now())
				).collect(Collectors.toList())
		);
		// 1 completed booking + 1 cancelled booking
		assertEquals(2, bookingService.getOngoingBookings((long) 1).size());
	}

	@Test
	public void getCompletedBookingHistoryTest() {
		when(bookingRepository.findAll()).thenReturn(
				Stream.of(
						new Booking((long) 1, (long) 1, "Booking 1", "ongoing", now()),
						new Booking((long) 2, (long) 1, "Booking 2", "completed", now()),
						new Booking((long) 3, (long) 1, "Booking 3", "ongoing", now()),
						new Booking((long) 4, (long) 1, "Booking 4", "cancelled", now())
				).collect(Collectors.toList())
		);
		// 1 completed booking
		assertEquals(1, bookingService.getCompletedBookingHistory((long) 1).size());
	}

	@Test
	public void setBookingStatusTest() {
		when(bookingRepository.findAll()).thenReturn(
				Stream.of(
						new Booking((long) 1, (long) 1, "Booking 1", "ongoing", now()),
						new Booking((long) 2, (long) 1, "Booking 2", "completed", now()),
						new Booking((long) 3, (long) 1, "Booking 3", "ongoing", now()),
						new Booking((long) 4, (long) 1, "Booking 4", "cancelled", now())
				).collect(Collectors.toList())
		);
		// try changing a valid booking id's status
		assertTrue(bookingService.setBookingStatus((long) 1, "completed"));
		assertTrue(bookingService.setBookingStatus((long) 3, "cancelled"));

		// try changing an invalid booking id's status
		assertFalse(bookingService.setBookingStatus((long) 5, "completed"));
	}

	@Test
	public void getBookingStatusTest() {
		when(bookingRepository.findAll()).thenReturn(
				Stream.of(
						new Booking((long) 1, (long) 1, "Booking 1", "ongoing", now()),
						new Booking((long) 2, (long) 1, "Booking 2", "completed", now()),
						new Booking((long) 3, (long) 1, "Booking 3", "ongoing", now()),
						new Booking((long) 4, (long) 1, "Booking 4", "cancelled", now())
				).collect(Collectors.toList())
		);
		// try getting a valid booking id's status
		assertEquals(bookingService.getBookingStatus((long) 1), "ongoing");
		assertEquals(bookingService.getBookingStatus((long) 2), "completed");

		// try getting an invalid booking id's status, it should return "N/A"
		assertEquals(bookingService.getBookingStatus((long) 5), "N/A");
	}

	@Test
	public void bookingIdExistTest() {
		when(bookingRepository.findAll()).thenReturn(
				Stream.of(
						new Booking((long) 1, (long) 1, "Booking 1", "ongoing", now()),
						new Booking((long) 2, (long) 1, "Booking 2", "completed", now()),
						new Booking((long) 3, (long) 1, "Booking 3", "ongoing", now()),
						new Booking((long) 4, (long) 1, "Booking 4", "cancelled", now())
				).collect(Collectors.toList())
		);
		// try getting a valid booking id
		assertTrue(bookingService.bookingIdExist((long) 1));
		assertTrue(bookingService.bookingIdExist((long) 2));
		assertTrue(bookingService.bookingIdExist((long) 3));
		assertTrue(bookingService.bookingIdExist((long) 4));

		// try getting an invalid booking id
		assertFalse(bookingService.bookingIdExist((long) 5));
	}

	@Test
	public void getBookingByIdTest() {
		Booking booking1 = new Booking((long) 1, (long) 1, "Booking 1", "ongoing", now());
		Booking booking2 = new Booking((long) 2, (long) 1, "Booking 2", "completed", now());
		when(bookingRepository.findAll()).thenReturn(
				Stream.of(
						booking1,
						booking2
				).collect(Collectors.toList())
		);

		assertEquals(bookingService.getBookingById((long) 1), booking1);
		assertEquals(bookingService.getBookingById((long) 2), booking2);
	}
}
