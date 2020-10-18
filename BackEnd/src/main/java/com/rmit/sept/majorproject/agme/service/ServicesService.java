package com.rmit.sept.majorproject.agme.service;

import com.rmit.sept.majorproject.agme.model.Provider;
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
	private ProviderService providerService;

	@Autowired
	public ServicesService(ServicesRepository servicesRepository, ProviderService providerService) {
		this.servicesRepository = servicesRepository;
		this.providerService = providerService;
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

	public List<Provider> getProvidersByServicesName(String services_name) {
		List<Long> providerIds = getAllServices()
				.stream()
				.filter(services -> services.getService_name().equals(services_name))
				.map(Services::getProvider_id)
				.collect(Collectors.toList());
		List<Provider> providers = new ArrayList<>();
		for (Long id : providerIds) {
			providers.add(providerService.getProvider(id));
		}
		return providers;
	}



	public boolean servicesIdExist(Long services_id) {
		return getAllServices()
				.stream()
				.anyMatch(services -> services.getService_id().equals(services_id));
	}

	public String getServiceNameById(Long services_id) {
		if (!servicesIdExist(services_id)) {
			return null;
		}
		return getAllServices()
				.stream()
				.filter(services -> services.getService_id().equals(services_id))
				.findFirst()
				.map(Services::getService_name)
				.orElseThrow(() -> new IllegalStateException(String.format("Services ID is not found", services_id)));
	}

	public static <T> Predicate<T> distinctByKey(
			Function<? super T, ?> keyExtractor) {

		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
}
