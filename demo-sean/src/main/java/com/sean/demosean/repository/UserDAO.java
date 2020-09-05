package com.sean.demosean.repository;

import com.sean.demosean.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserDAO {
    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "Janet Jones", null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "Sean Tan", null));
    }

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }
}
