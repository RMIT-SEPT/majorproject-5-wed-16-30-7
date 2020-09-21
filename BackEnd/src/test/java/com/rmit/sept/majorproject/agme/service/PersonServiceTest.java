package com.rmit.sept.majorproject.agme.service;

import com.rmit.sept.majorproject.agme.model.Person;
import com.rmit.sept.majorproject.agme.repositories.PersonRepository;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PersonServiceTest {

	@Autowired
	private PersonService personService;

	@MockBean
	private PersonRepository personRepository;

	@Test
	void getAllPersonsTest() {
		when(personRepository.findAll()).thenReturn(
				Stream.of(
						new Person((long) 1, "Bob", "bob123", "1234", "c"),
						new Person((long) 2, "Adam", "adam123", "pass123", "c")

				).collect(Collectors.toList())
		);
		assertEquals(2, personService.getAllPersons().size());
	}

	@Test
	void loginTest() {
		when(personRepository.findAll()).thenReturn(
				Stream.of(
						new Person((long) 1, "Bob", "bob123", "1234", "c"),
						new Person((long) 2, "Adam", "adam123", "pass123", "c")

				).collect(Collectors.toList())
		);
		assertTrue(personService.Login("bob123", "1234"));
		assertTrue(personService.Login("adam123", "pass123"));
		assertFalse(personService.Login("bob1234", "1234"));
		assertFalse(personService.Login("adam123", "123456"));
	}

	@Test
	void getPersonAccountTypeByIdTest() {
		when(personRepository.findAll()).thenReturn(
				Stream.of(
						new Person((long) 1, "Bob", "bob123", "1234", "c"),
						new Person((long) 2, "Adam", "adam123", "pass123", "a")

				).collect(Collectors.toList())
		);
		assertEquals('c', personService.getPersonAccountTypeById((long) 1));
		assertEquals('a', personService.getPersonAccountTypeById((long) 2));
	}

	@Test
	void addPersonTest() {
		Person bob = new Person((long) 1, "Bob", "bob123", "1234", "c");
		when(personRepository.save(bob)).thenReturn(bob);
		assertTrue(personService.addPerson(bob));
	}

	@Test
	void getPersonByIdTest() {
		Person bob = new Person((long) 1, "Bob", "bob123", "1234", "c");
		Person adam = new Person((long) 2, "Adam", "adam123", "pass123", "a");
		when(personRepository.findAll()).thenReturn(
				Stream.of(bob,adam).collect(Collectors.toList())
		);
		assertEquals(bob, personService.getPersonById((long) 1));
		assertEquals(adam, personService.getPersonById((long) 2));
	}

	@Test
	void getPersonByUsernameTest() {
		Person bob = new Person((long) 1, "Bob", "bob123", "1234", "c");
		Person adam = new Person((long) 2, "Adam", "adam123", "pass123", "a");
		when(personRepository.findAll()).thenReturn(
				Stream.of(bob, adam).collect(Collectors.toList())
		);
		assertEquals(bob, personService.getPersonByUsername("bob123"));
		assertEquals(adam, personService.getPersonByUsername("adam123"));
	}

	@Test
	void personIdExistTest() {
		Person bob = new Person((long) 1, "Bob", "bob123", "1234", "c");
		Person adam = new Person((long) 2, "Adam", "adam123", "pass123", "a");
		when(personRepository.findAll()).thenReturn(
				Stream.of(bob, adam).collect(Collectors.toList())
		);
		assertTrue(personService.personIdExist((long) 1));
		assertTrue(personService.personIdExist((long) 2));
		assertFalse(personService.personIdExist((long) 3));
	}

	@Test
	void updatePersonTest() {
		Person bob_updated = new Person((long) 1, "Bob2", "bob234", "pass123", "c");
		when(personRepository.findAll()).thenReturn(
				Stream.of(bob_updated).collect(Collectors.toList())
		);
		when(personRepository.save(bob_updated)).thenReturn(bob_updated);
		assertTrue(personService.updatePerson((long) 1, bob_updated));
		verify(personRepository, times(1)).save(bob_updated);
	}

	@Test
	void updatePersonPasswordTest() {
		Person bob_updated = new Person((long) 1, "Bob", "bob123", "pass123", "c");
		when(personRepository.findAll()).thenReturn(
				Stream.of(bob_updated).collect(Collectors.toList())
		);
		when(personRepository.save(bob_updated)).thenReturn(bob_updated);
		assertTrue(personService.updatePersonPassword((long) 1, "pass123"));
		verify(personRepository, times(1)).save(bob_updated);
	}

	@Test
	void deletePersonByIdTest() {
		Person bob= new Person((long) 1, "Bob", "bob123", "pass123", "c");
		when(personRepository.findAll()).thenReturn(
				Stream.of(bob).collect(Collectors.toList())
		);
		personService.deletePersonById((long) 1);
		verify(personRepository, times(1)).deleteById((long) 1);
	}
}