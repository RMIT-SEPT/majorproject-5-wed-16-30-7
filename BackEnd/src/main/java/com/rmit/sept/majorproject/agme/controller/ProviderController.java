package com.rmit.sept.majorproject.agme.controller;

import com.rmit.sept.majorproject.agme.model.Provider;
import com.rmit.sept.majorproject.agme.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/provider")
@CrossOrigin("*") // accepting post/get from any sources, change if needed
public class ProviderController {
	private ProviderService providerService;

	@Autowired
	public ProviderController(ProviderService providerService) {
		this.providerService = providerService;
	}

	@GetMapping("all-providers")
	public Iterable<Provider> getAllProviders() {
		return  providerService.getAllProviders();
	}

	// might need this function when you choose a service then list the providers of a service
	@GetMapping("get-provider-name-by-id/{providerId}")
	public ResponseEntity<?> getProviderNameById(@PathVariable("providerId") Long provider_id) {
		String providerName = providerService.getProviderName(provider_id);
		return providerName != null ?
				new ResponseEntity<>(providerName, HttpStatus.OK) : new ResponseEntity<>("Provider ID does not exist", HttpStatus.BAD_REQUEST);
	}

	@PostMapping("add-provider")
	public ResponseEntity<?> addProvider(@Valid @RequestBody Provider provider, BindingResult result) {
		if (result.hasErrors()){
			return new ResponseEntity<String>("Invalid Provider Object", HttpStatus.BAD_REQUEST);
		}
		return providerService.addProvider(provider) ?
				new ResponseEntity<>(provider, HttpStatus.CREATED) : new ResponseEntity<>("Failed to create provider", HttpStatus.BAD_REQUEST);
	}
}
