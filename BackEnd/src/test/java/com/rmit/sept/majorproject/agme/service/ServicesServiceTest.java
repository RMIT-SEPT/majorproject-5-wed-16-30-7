package com.rmit.sept.majorproject.agme.service;

import com.rmit.sept.majorproject.agme.model.Booking;
import com.rmit.sept.majorproject.agme.model.Provider;
import com.rmit.sept.majorproject.agme.model.Services;
import com.rmit.sept.majorproject.agme.repositories.BookingRepository;
import com.rmit.sept.majorproject.agme.repositories.ProviderRepository;
import com.rmit.sept.majorproject.agme.repositories.ServicesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.util.DateUtil.now;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServicesServiceTest {
	@Autowired
	private ServicesService servicesService;

	@MockBean
	private ServicesRepository servicesRepository;

	@MockBean
	private ProviderRepository providerRepository;

	@Test
	public void getAllServicesTest() {
		Services service1 = new Services((long) 1, (long) 1, "service1");
		Services service2 = new Services((long) 1, (long) 1, "service2");
		when(servicesRepository.findAll()).thenReturn(
				Stream.of(
						service1,
						service2
				).collect(Collectors.toList())
		);
		assertEquals(2, servicesService.getAllServices().size());
	}

	@Test
	public void getServicesDistinctTest() {
		Services service1 = new Services((long) 1, (long) 1, "Haircut");
		Services service2 = new Services((long) 2, (long) 2, "Dental");
		Services service3 = new Services((long) 3, (long) 3, "Haircut");
		Services service4 = new Services((long) 4, (long) 4, "Gym");
		Services service5 = new Services((long) 5, (long) 5, "Gym");
		when(servicesRepository.findAll()).thenReturn(
				Stream.of(
						service1,
						service2,
						service3,
						service4,
						service5
				).collect(Collectors.toList())
		);
		// 3 distinct services
		assertEquals(3, servicesService.getServicesDistinct().size());
	}

	@Test
	public void getProvidersByServicesNameTest() {
		Services service1 = new Services((long) 1, (long) 1, "Haircut");
		Services service2 = new Services((long) 2, (long) 2, "Dental");
		Services service3 = new Services((long) 3, (long) 3, "Haircut");

		List<Provider> providerList = new ArrayList<>();
		Provider provider1 = new Provider((long) 1, "provider1");
		Provider provider3 = new Provider((long) 3, "provider3");
		providerList.add(provider1);
		providerList.add(provider3);


		when(servicesRepository.findAll()).thenReturn(
				Stream.of(
						service1,
						service2,
						service3
				).collect(Collectors.toList())
		);

		when(providerRepository.findAll()).thenReturn(
				Stream.of(
						provider1,
						provider3
				).collect(Collectors.toList())
		);

		// compare each provider by their contents
		int i = 0;
		for (Provider p : providerList) {
			assertEquals(p.getProvider_id(), servicesService.getProvidersByServicesName("Haircut").get(i).getProvider_id());
			assertEquals(p.getProvider_name(), servicesService.getProvidersByServicesName("Haircut").get(i).getProvider_name());
			i++;
		}
	}

	@Test
	public void servicesIdExistTest() {
		Services service1 = new Services((long) 1, (long) 1, "Haircut");
		Services service2 = new Services((long) 2, (long) 2, "Dental");
		when(servicesRepository.findAll()).thenReturn(
				Stream.of(
						service1, service2
				).collect(Collectors.toList())
		);
		// try getting a valid services id
		assertTrue(servicesService.servicesIdExist((long) 1));
		assertTrue(servicesService.servicesIdExist((long) 2));

		// try getting an invalid provider id
		assertFalse(servicesService.servicesIdExist((long) 3));
	}

	@Test
	public void getServiceNameByIdTest() {
		Services service1 = new Services((long) 1, (long) 1, "Haircut");
		Services service2 = new Services((long) 2, (long) 2, "Dental");
		when(servicesRepository.findAll()).thenReturn(
				Stream.of(
						service1, service2
				).collect(Collectors.toList())
		);
		assertEquals(service1.getService_name(), servicesService.getServiceNameById((long) 1));
		assertEquals(service2.getService_name(), servicesService.getServiceNameById((long) 2));
	}
}
