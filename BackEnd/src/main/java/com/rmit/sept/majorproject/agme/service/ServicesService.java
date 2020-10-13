package com.rmit.sept.majorproject.agme.service;

import com.rmit.sept.majorproject.agme.model.Services;
import com.rmit.sept.majorproject.agme.repositories.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ServicesService {
	private static ServicesRepository servicesRepository;

	@Autowired
	public ServicesService(ServicesRepository servicesRepository) {
		this.servicesRepository = servicesRepository;
	}

	public static List<Services> getAllServices() {
		return servicesRepository.findAll();
	}

	public static List<Services> getServicesDistinct() {
		return getAllServices()
				.stream()
				.filter(distinctByKey(services -> services.getService_name()))
				.collect(Collectors.toList());
	}

	public boolean addServices(Services services) {
		if (servicesIdExist(services.getService_id())) {
			return false;
		}
		servicesRepository.save(services);
		return true;
	}

	public List<Long> getProviderIdsByServicesName(String services_name) {
		return getAllServices()
				.stream()
				.filter(services -> services.getService_name().equals(services_name))
				.map(Services::getProvider_id)
				.collect(Collectors.toList());
	}



	public boolean servicesIdExist(Long services_id) {
		return getAllServices()
				.stream()
				.anyMatch(services -> services.getService_id().equals(services_id));
	}

	public static <T> Predicate<T> distinctByKey(
			Function<? super T, ?> keyExtractor) {

		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
}
