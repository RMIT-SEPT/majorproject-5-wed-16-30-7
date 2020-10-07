package com.rmit.sept.majorproject.agme.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserRepository  {

    User findByUsername(String username);
    User getById(Long id);
}