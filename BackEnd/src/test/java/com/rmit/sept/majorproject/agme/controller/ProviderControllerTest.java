package com.rmit.sept.majorproject.agme.controller;

import com.rmit.sept.majorproject.agme.model.Booking;
import com.rmit.sept.majorproject.agme.model.BookingInfo;
import com.rmit.sept.majorproject.agme.model.Person;
import com.rmit.sept.majorproject.agme.model.Provider;
import com.rmit.sept.majorproject.agme.service.BookingService;
import com.rmit.sept.majorproject.agme.service.ProviderService;
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
class ProviderControllerTest {

	private MockMvc mockMvc;

	@Mock
	private ProviderService providerService;

	@InjectMocks
	private ProviderController providerController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(providerController).build();
	}

	@Test
	void getAllProvidersTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/provider/all-providers"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void getProviderByIdTest() throws Exception {
		Provider provider1 = new Provider((long) 1, "provider1");

		when(providerService.getProvider(provider1.getProvider_id())).thenReturn(provider1);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/provider/get-provider-by-id/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}