package com.rmit.sept.majorproject.agme.controller;

import com.rmit.sept.majorproject.agme.model.Booking;
import com.rmit.sept.majorproject.agme.model.BookingInfo;
import com.rmit.sept.majorproject.agme.model.Provider;
import com.rmit.sept.majorproject.agme.model.Services;
import com.rmit.sept.majorproject.agme.service.BookingService;
import com.rmit.sept.majorproject.agme.service.ServicesService;
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
class ServicesControllerTest {

	private MockMvc mockMvc;

	@Mock
	private ServicesService servicesService;

	@InjectMocks
	private ServicesController servicesController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(servicesController).build();
	}

	@Test
	void getAllServicesDistinctTest() throws Exception {
		Services service1 = new Services((long) 1, (long) 1, "Haircut");
		Services service2 = new Services((long) 2, (long) 2, "Dental");
		Services service4 = new Services((long) 4, (long) 4, "Gym");

//		when(servicesService.getServicesDistinct()).thenReturn(
//				Stream.of(
//						// the first service of with the distinct name
//						service1, service2, service4
//				).collect(Collectors.toList())
//		);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/service/all-services-distinct"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void getAllProvidersByServicesNameTest() throws Exception {
		Provider provider1 = new Provider((long) 1, "provider1");
		Provider provider3 = new Provider((long) 2, "provider3");

		when(servicesService.getProvidersByServicesName("Haircut")).thenReturn(
				Stream.of(
						provider1, provider3
				).collect(Collectors.toList())
		);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/service/get-providers/Haircut"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}


}