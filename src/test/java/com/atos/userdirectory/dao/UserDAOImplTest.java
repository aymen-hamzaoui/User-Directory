package com.atos.userdirectory.dao;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.atos.userdirectory.entity.GenderType;
import com.atos.userdirectory.entity.User;

@SpringBootTest
class UserDAOImplTest {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private EntityManager entityManager;
	
	@Test
	void testFindByUsernameOk() {
		
		// Arrange
		User testUser = new User();
		
		// Act
		testUser = userDAO.findByUsername("aymenhamzaoui");
		
		// Assert
		Assertions.assertEquals("aymenhamzaoui", testUser.getUsername());
		
	}
	
	@Test
	@Transactional
	void testSaveOk() {
		
		// Arrange
		User theUser = new User("aymentestDAO", GenderType.M, LocalDate.of(1998, 12, 31), "FRA", "0763951737");
		
		// Act
		userDAO.save(theUser);
		
		User resultUser = userDAO.findByUsername("aymentestDAO");
		
		// Assert
		Assertions.assertEquals(theUser, resultUser);
		
	}
	


}
