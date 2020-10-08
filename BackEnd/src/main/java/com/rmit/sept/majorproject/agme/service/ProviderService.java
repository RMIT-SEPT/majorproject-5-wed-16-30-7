package com.rmit.sept.majorproject.agme.service;

import com.rmit.sept.majorproject.agme.model.Provider;
import com.rmit.sept.majorproject.agme.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {
	private static ProviderRepository providerRepository;

	@Autowired
	public ProviderService(ProviderRepository providerRepository) {
		this.providerRepository = providerRepository;
	}

	public static List<Provider> getAllProviders() {
		return providerRepository.findAll();
	}

	public boolean addProvider(Provider provider) {
		if (providerIdExist(provider.getProdiver_id())) {
			return false;
		}
		providerRepository.save(provider);
		return true;
	}

	public String getProviderName(Long provider_id) {
		if (!providerIdExist(provider_id)) {
			return null;
		}
		return getAllProviders()
				.stream()
				.filter(provider -> provider.getProdiver_id().equals((provider_id)))
				.findFirst()
				.orElseThrow(() -> new IllegalStateException(String.format("Provider ID %s not found", provider_id)))
				.getProvider_name();

	}

	public boolean providerIdExist(Long provider_id) {
		return getAllProviders()
				.stream()
				.anyMatch(provider -> provider.getProdiver_id().equals(provider_id));
	}
}
