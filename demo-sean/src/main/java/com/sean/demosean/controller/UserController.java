package com.sean.demosean.controller;

import com.sean.demosean.profile.UserProfile;
import com.sean.demosean.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserProfile> getUserProfiles() {
		return userService.getUserProfiles();
	}
}
