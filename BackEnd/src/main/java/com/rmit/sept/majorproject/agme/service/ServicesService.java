package com.rmit.sept.majorproject.agme.service;

import com.rmit.sept.majorproject.agme.model.Services;
import com.rmit.sept.majorproject.agme.repositories.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
				.distinct()
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
}
