package com.rmit.sept.majorproject.agme.controller;

import com.rmit.sept.majorproject.agme.model.Person;
import com.rmit.sept.majorproject.agme.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("login")
    public void Login(@Valid @RequestBody Person person, BindingResult result) {
        if (personService.Login(person)) {

        }
    }

    @PostMapping("")
    public ResponseEntity<?> createNewPerson(@Valid @RequestBody Person person, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Person Object", HttpStatus.BAD_REQUEST);
        }
        boolean personCreated = personService.addPerson(person);
        if (personCreated) {
            return new ResponseEntity<Person>(person, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<Person>(person, HttpStatus.BAD_REQUEST);
        }
    }

    // general update
    @PostMapping("update/{personId}")
    public ResponseEntity<?> updatePerson(@PathVariable("personId") Long id, @Valid @RequestBody Person person, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Person Object", HttpStatus.BAD_REQUEST);
        }
        boolean personUpdated = personService.updatePerson(id, person);
        if (personUpdated) {
            return new ResponseEntity<Person>(person, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<Person>(person, HttpStatus.BAD_REQUEST);
        }
    }

    //specific updates, can add more following the same pattern
    // do not send in the usual json format {password: "password"} but just "password" removing the quotation also
    @PostMapping("update-password/{personId}")
    public ResponseEntity<?> updatePersonPassword(@PathVariable("personId") Long id, @Valid @RequestBody String password, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid password", HttpStatus.BAD_REQUEST);
        }
        boolean personUpdated = personService.updatePersonPassword(id, password);
        if (personUpdated) {
            return new ResponseEntity<Person>(personService.getPersonById(id), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<Person>(personService.getPersonById(id), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public Iterable<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @DeleteMapping("{personId}")
    public void deletePersonById(@PathVariable("personId") Long id) {
        personService.deletePersonById(id);
    }

}
