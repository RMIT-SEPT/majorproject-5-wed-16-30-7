package com.rmit.sept.majorproject.agme.service;

import com.rmit.sept.majorproject.agme.model.Person;
import com.rmit.sept.majorproject.agme.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;

@Service
public class PersonService {

    private static PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public static List<Person> getAllPersons() {
        return personRepository.findAll();
    }


    public boolean Login(String username, String password) {
        return verifyPassword.test(username, password);
    }

    public static BiPredicate<String,String> verifyPassword = (username, password) ->
            getPersonByUsername(username)
            .getPassword()
            .equals(password);

    public char getPersonAccountTypeById(Long id) {
        if(personIdExist(id)) {
            return getPersonById(id).getAccountType();
        }
        else {
            return 'n';
        }
    }

    public boolean addPerson(Person person) {
        if (personIdExist(person.getId())) {
            return false;
        }
        personRepository.save(person);
        return true;
    }

    public static Person getPersonById(Long id) {
        return personRepository.findAll()
                .stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User ID %s not found", id)));
    }
    
    public static Person getPersonByUsername(String username) {
        return personRepository.findAll()
                .stream()
                .filter(person -> person.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Username %s not found", username)));
    }

    public boolean personIdExist(Long id) {
        return getAllPersons()
                .stream()
                .anyMatch(person -> person.getId().equals(id));
    }

    public boolean updatePerson(Long id, Person person) {
        if (personIdExist(id)) {
            person.setId(id);
            personRepository.save(person);
            return true;
        }
        return false;
    }

    public boolean updatePersonPassword(Long id, String password) {
        if (personIdExist(id)) {
            Person p = getPersonById(id);
            p.setPassword(password);
            personRepository.save(p);
            return true;
        }
        return false;
    }


    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }

}
