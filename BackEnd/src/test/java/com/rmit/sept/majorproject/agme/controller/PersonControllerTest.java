package com.rmit.sept.majorproject.agme.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmit.sept.majorproject.agme.model.Person;
import com.rmit.sept.majorproject.agme.service.PersonService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class PersonControllerTest {

	private MockMvc mockMvc;

	@Mock
	private PersonService personService;

	@InjectMocks
	private PersonController personController;

	ObjectMapper om = new ObjectMapper();

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
	}

	@Test
	void LoginTest() throws Exception {
		String username = "bob123";
		String password = "1234";
		when(personService.Login(username, password)).thenReturn(true);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/person/login/bob123")
		.content(password))
				.andExpect(MockMvcResultMatchers.status().isAccepted());


		// wrong username
		mockMvc.perform(MockMvcRequestBuilders.post("/api/person/login/bob1234")
				.content(password))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

		// wrong password
		String wrongPassword = "12345";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/person/login/bob1234")
				.content(wrongPassword))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}



	@Test
	void registerTest() throws Exception {
//		String json = "{" +
//				" \"name\":\"Bob\"," +
//				" \"username\":\"bob123\"" +
//				" \"password\":\"1234\"" +
//				" \"accountType\":\"c\"" +
//				"}";
		Person bob = new Person((long) 1, "Bob", "bob123", "1234", 'c');
		String jsonRequest = om.writeValueAsString(bob);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/person/")
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Bob")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("bob123")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("1234")));
	}

	@Test
	void updatePersonTest() throws Exception {
		Person bob = new Person((long) 1, "Bob", "bob123", "1234", 'c');
		Person updatedBob = new Person((long) 1, "BobUpdated", "bob123", "1234", 'c');
		String jsonRequest = om.writeValueAsString(bob);

		when(personService.updatePerson(bob.getId(), updatedBob)).thenReturn(true);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/person/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(MockMvcResultMatchers.status().isAccepted());
	}

	@Test
	void updatePersonPasswordTest() throws Exception {
		Person bob = new Person((long) 1, "Bob", "bob123", "1234", 'c');
		String newPassword = "12345";
		when(personService.updatePersonPassword(bob.getId(), newPassword)).thenReturn(true);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/person/update-password/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(newPassword))
				.andExpect(MockMvcResultMatchers.status().isAccepted());
	}

	@Test
	void getPersonAccountTypeTest() throws Exception {
		Person bob = new Person((long) 1, "Bob", "bob123", "1234", 'c');

		when(personService.getPersonAccountTypeById(bob.getId())).thenReturn(bob.getAccountType());

		mockMvc.perform(MockMvcRequestBuilders.get("/api/person/account-type/1"))
				.andExpect(MockMvcResultMatchers.status().isAccepted())
				.andExpect(MockMvcResultMatchers.content().string("\"c\""));
	}

	@Test
	void getAllPersonsTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/person"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}