package com.rmit.sept.majorproject.agme.service;

import com.rmit.sept.majorproject.agme.model.Booking;
import com.rmit.sept.majorproject.agme.model.Provider;
import com.rmit.sept.majorproject.agme.repositories.BookingRepository;
import com.rmit.sept.majorproject.agme.repositories.ProviderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.util.DateUtil.now;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProviderServiceTest {
	@Autowired
	private ProviderService providerService;

	@MockBean
	private ProviderRepository providerRepository;

	@Test
	public void getAllProvidersTest() {
		when(providerRepository.findAll()).thenReturn(
				Stream.of(
						new Provider((long) 1, "provider1"),
						new Provider((long) 2, "provider2")
				).collect(Collectors.toList())
		);
		assertEquals(2, providerService.getAllProviders().size());
	}

	@Test
	public void getProviderTest() {
		Provider provider1 = new Provider((long) 1, "provider1");
		Provider provider2 = new Provider((long) 2, "provider2");
		when(providerRepository.findAll()).thenReturn(
				Stream.of(
						provider1,
						provider2
				).collect(Collectors.toList())
		);
		assertEquals(provider1, providerService.getProvider((long) 1));
		assertEquals(provider2, providerService.getProvider((long) 2));
	}

	@Test
	public void getProviderNameTest() {
		Provider provider1 = new Provider((long) 1, "provider1");
		Provider provider2 = new Provider((long) 2, "provider2");
		when(providerRepository.findAll()).thenReturn(
				Stream.of(
						provider1,
						provider2
				).collect(Collectors.toList())
		);
		assertEquals(provider1.getProvider_name(), providerService.getProviderName((long) 1));
		assertEquals(provider2.getProvider_name(), providerService.getProviderName((long) 2));
	}

	@Test
	public void providerIdExistTest() {
		Provider provider1 = new Provider((long) 1, "provider1");
		Provider provider2 = new Provider((long) 2, "provider2");
		when(providerRepository.findAll()).thenReturn(
				Stream.of(
						provider1,
						provider2
				).collect(Collectors.toList())
		);
		// try getting a valid provider id
		assertTrue(providerService.providerIdExist((long) 1));
		assertTrue(providerService.providerIdExist((long) 2));

		// try getting an invalid provider id
		assertFalse(providerService.providerIdExist((long) 3));
	}
}
