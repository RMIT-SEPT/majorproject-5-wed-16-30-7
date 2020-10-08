package com.rmit.sept.majorproject.agme.controller;

import com.rmit.sept.majorproject.agme.model.Services;
import com.rmit.sept.majorproject.agme.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/service")
@CrossOrigin("*") // accepting post/get from any sources, change if needed
public class ServicesController {
	private ServicesService servicesService;

	@Autowired
	public ServicesController(ServicesService servicesService) {
		this.servicesService = servicesService;
	}

	@GetMapping("all-services-distinct")
	public Iterable<Services> getAllServicesDistinct() {
		return servicesService.getServicesDistinct();
	}

	@GetMapping("get-provider-ids/{serviceName}")
	public Iterable<Long> getAllProviderIdsByServicesName(@PathVariable("serviceName") String serviceName) {
		return servicesService.getProviderIdsByServicesName(serviceName);
	}

	@PostMapping("add-service")
	public ResponseEntity<?> addService(@Valid @RequestBody Services services, BindingResult result) {
		if (result.hasErrors()){
			return new ResponseEntity<>("Invalid Services Object", HttpStatus.BAD_REQUEST);
		}
		return servicesService.addServices(services) ?
				new ResponseEntity<>(services, HttpStatus.CREATED) : new ResponseEntity<>("Failed to create service", HttpStatus.BAD_REQUEST);
	}
}
