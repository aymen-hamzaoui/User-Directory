package com.atos.userdirectory.rest;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class UserRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testAddUserOk() throws Exception {
		
		// Arrange
	    Random random = new Random();

	    String generatedString = random.ints(97, 122 + 1)
	      .limit(10)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    
		String theUser = "{\"username\": \""+generatedString
				+ "\",\"gender\": \"M\",\"birthDate\": \"1998-12-31\","
				+ "\"country\": \"FRA\",\"phone\": \"0669291891\"}";

		// Act 
		mockMvc.perform(post("/api/users")
				.contentType(MediaType.APPLICATION_JSON).content(theUser))
		
		// Assert
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.username", is(generatedString)));

	}

	@Test
	public void testAddUserAlreadyExistant() throws Exception {
		
		// Arrange
		String theUser = "{\"username\": \"aymenhamzaoui"
				+ "\",\"gender\": \"M\",\"birthDate\": \"1998-12-31\","
				+ "\"country\": \"FRA\",\"phone\": \"0669291891\"}";

		// Act
		mockMvc.perform(post("/api/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(theUser))
		
		// Assert
				.andExpect(status().isConflict())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.message", is("Username - aymenhamzaoui - already exists!")));

	}
	

	@Test
	public void testFinByUsernameFound() throws Exception {
		
		// Act
		mockMvc.perform(get("/api/users/aymenhamzaoui"))
		
		// Assert
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.username", is("aymenhamzaoui")));

	}

	@Test
	public void testFinByUsernameNotFound() throws Exception {

		// Act
		mockMvc.perform(get("/api/users/aymen2"))
		
		// Assert
		.andExpect(status().isNotFound());

	}

}
