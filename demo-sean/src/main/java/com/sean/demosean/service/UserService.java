package com.sean.demosean.service;

import com.sean.demosean.profile.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	private final UserDataAccessService userDataAccessService;

	@Autowired
	public UserService(UserDataAccessService userDataAccessService) {
		this.userDataAccessService = userDataAccessService;
	}

	public List<UserProfile> getUserProfiles() {
		return userDataAccessService.getUserProfiles();
	}
}
