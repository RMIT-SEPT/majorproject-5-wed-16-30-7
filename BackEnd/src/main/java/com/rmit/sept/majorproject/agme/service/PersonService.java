package com.rmit.sept.majorproject.agme.service;

import com.rmit.sept.majorproject.agme.model.Person;
import com.rmit.sept.majorproject.agme.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Iterable<Person> getAllPersons() {
        return personRepository.findAll();
    }

//    public Person saveOrUpdatePerson(Person person) {
//
//        //logic
//        return personRepository.save(person);
//    }

    public boolean addPerson(Person person) {
        if (personIdExist(person.getId())) {
            return false;
        }
        personRepository.save(person);
        return true;
    }

    public Person getPersonById(Long id) {
        for (Person p: getAllPersons()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean personIdExist(Long id) {
        for (Person p: getAllPersons()) {
            if (p.getId() == id) {
                return true;
            }
        }
        return false;
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
            Person person = getPersonById(id);
            person.setPassword(password);
            personRepository.save(person);
            return true;
        }
        return false;
    }

    public void deletePerson(Person person) {
        personRepository.delete(person);
    }

    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }

}
