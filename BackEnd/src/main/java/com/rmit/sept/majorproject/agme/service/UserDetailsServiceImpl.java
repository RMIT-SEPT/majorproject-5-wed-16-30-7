package com.rmit.sept.majorproject.agme.service;

import com.rmit.sept.majorproject.agme.model.Person;
import com.rmit.sept.majorproject.agme.repositories.PersonRepository;
import com.rmit.sept.majorproject.agme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(s);
        if(person == null) new UsernameNotFoundException("Person not found");
        return person;
    }

    @Transactional
    public Person loadUserById(Long id){
        Person person = personRepository.getById(id);
        if(person == null) new UsernameNotFoundException("Person not found");
        return person;
    }
}
