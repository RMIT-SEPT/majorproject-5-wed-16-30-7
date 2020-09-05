package com.sean.demosean.service;

import com.sean.demosean.profile.UserProfile;
import com.sean.demosean.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDataAccessService {

    private final UserDAO userDao;

    @Autowired
    public UserDataAccessService(UserDAO userDao) {
        this.userDao = userDao;
    }

    List<UserProfile> getUserProfiles() {
        return userDao.getUserProfiles();
    }
}
