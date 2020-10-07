package com.rmit.sept.majorproject.agme.controller;
import com.rmit.sept.majorproject.agme.model.Person;
import com.rmit.sept.majorproject.agme.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/person")
@CrossOrigin("*") // accepting post/get from any sources, change if needed
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //TODO: return user id
    @PostMapping("login/{username}")
    public ResponseEntity<?> Login(@PathVariable("username") String username, @Valid @RequestBody String password, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<>("Invalid password", HttpStatus.BAD_REQUEST);
        }
        return personService.Login(username, password) ?
                new ResponseEntity<>(HttpStatus.ACCEPTED) : new ResponseEntity<>("Wrong password, username given: " + username + ", password given: " + password, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("register")
    public ResponseEntity<?> Register(@Valid @RequestBody Person person, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Person Object", HttpStatus.BAD_REQUEST);
        }
        return personService.addPerson(person) ?
                new ResponseEntity<Person>(person, HttpStatus.CREATED) : new ResponseEntity<String>("Failed to create person", HttpStatus.BAD_REQUEST);
    }


    // general update
    @PostMapping("update/{personId}")
    public ResponseEntity<?> updatePerson(@PathVariable("personId") Long id, @Valid @RequestBody Person person, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Person Object", HttpStatus.BAD_REQUEST);
        }
        return personService.updatePerson(id, person) ?
                new ResponseEntity<>(person, HttpStatus.ACCEPTED) : new ResponseEntity<>(person, HttpStatus.BAD_REQUEST);
    }

    //specific updates, can add more following the same pattern
    // do not send in the usual json format {password: "password"} but just the actual raw password
    @PostMapping("update-password/{personId}")
    public ResponseEntity<?> updatePersonPassword(@PathVariable("personId") Long id, @Valid @RequestBody String password, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid password", HttpStatus.BAD_REQUEST);
        }
        return personService.updatePersonPassword(id, password) ?
                new ResponseEntity<>(HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("account-type/{personId}")
    public ResponseEntity<?> getPersonAccountType(@PathVariable("personId") Long id) {
        String accountType = personService.getPersonAccountTypeById(id);
        return accountType != null ?
                new ResponseEntity<>(accountType, HttpStatus.ACCEPTED) : new ResponseEntity<>(accountType, HttpStatus.BAD_REQUEST);
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
