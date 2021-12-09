package com.atos.userdirectory.service;

import static org.mockito.Mockito.doReturn;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.atos.userdirectory.dao.UserDAO;
import com.atos.userdirectory.entity.GenderType;
import com.atos.userdirectory.entity.User;

@SpringBootTest
class UserServiceImplTest {

	@Autowired
	private UserService userService;
	
    @MockBean
    private UserDAO userDAO;

	@Test
	void testTrimUsername() {
		
		// Arrange
		LocalDate date = LocalDate.of(1998, 12, 31);

		User theUser = new User("aymen   ", GenderType.M, date, "FRA", "0763951737");
		User theUser2 = new User("    aymen", GenderType.M, date, "FRA", "0763951737");

		// Act
		theUser = userService.customizeUser(theUser);
		theUser2 = userService.customizeUser(theUser2);

		// Assert
		Assertions.assertEquals("aymen", theUser.getUsername());
		Assertions.assertEquals("aymen", theUser2.getUsername());

	}

	@Test
	void testCustomizeCountry() {

		// Arrange
		LocalDate date = LocalDate.of(1998, 12, 31);

		User theUser1 = new User("aymen", GenderType.M, date, "france", "0763951737");
		User theUser2 = new User("aymen", GenderType.M, date, "fr", "0763951737");
		User theUser3 = new User("aymen", GenderType.M, date, "FRA", "0763951737");
		User theUser4 = new User("aymen", GenderType.M, date, "fraNce", "0763951737");
		User theUser5 = new User("aymen", GenderType.M, date, "  fraNce  ", "0763951737");
		User theUser6 = new User("aymen", GenderType.M, date, "AZEA", "0763951737");
		User theUser7 = new User("aymen", GenderType.M, date, null, "0763951737");

		// Act
		String result1 = userService.customizeCountry(theUser1.getCountry());
		String result2 = userService.customizeCountry(theUser2.getCountry());
		String result3 = userService.customizeCountry(theUser3.getCountry());
		String result4 = userService.customizeCountry(theUser4.getCountry());
		String result5 = userService.customizeCountry(theUser5.getCountry());
		String result6 = userService.customizeCountry(theUser6.getCountry());
		String result7 = userService.customizeCountry(theUser7.getCountry());

		// Assert
		Assertions.assertEquals("FRA", result1);
		Assertions.assertEquals("FRA", result2);
		Assertions.assertEquals("FRA", result3);
		Assertions.assertEquals("FRA", result4);
		Assertions.assertEquals("FRA", result5);
		Assertions.assertEquals("Azea", result6);
		Assertions.assertNull(result7);

	}

	@Test
	public void testCheckValidUserNotAdult() {
		
		// Arrange
		boolean thrown = false;
		String ExceptionName = "";
		LocalDate date = LocalDate.of(2009, 12, 31);
		User theUser = new User("aymen", GenderType.M, date, "france", "0763951737");

		// Act
		try {
			userService.checkValidUser(theUser);
		} catch (Exception e) {
			ExceptionName = e.getClass().getSimpleName();
			thrown = true;
		}

		// Assert
		Assertions.assertTrue(thrown);
		Assertions.assertEquals("UserNotAdultException", ExceptionName);
	}

	@Test
	public void testCheckValidUserNotFrenshResident() {

		// Arrange
		boolean thrown = false;
		String ExceptionName = "";
		LocalDate date = LocalDate.of(1998, 12, 31);
		User theUser = new User("aymen", GenderType.M, date, "MAR", "0763951737");

		// Act
		try {
			userService.checkValidUser(theUser);
		} catch (Exception e) {
			ExceptionName = e.getClass().getSimpleName();
			thrown = true;
		}

		// Assert
		Assertions.assertTrue(thrown);
		Assertions.assertEquals("UserNotFrenshResidentException", ExceptionName);
	}
	
	@Test
	public void testCheckValidUser() {
		
		// Arrange
		boolean thrown = false;
		LocalDate date = LocalDate.of(1998, 12, 31);
		User theUser = new User("aymen", GenderType.M, date, "FRA", "0763951737");
		
		// Act
		try {
			userService.checkValidUser(theUser);
		} catch (Exception e) {
			thrown = true;
		}
		
		// Assert
		Assertions.assertFalse(thrown);
	}
	
	@Test
	public void testFindByUsernameFound() {
		
		// Arrange
		LocalDate date = LocalDate.of(1998, 12, 31);
		User theUser = new User("aymen", GenderType.M, date, "FRA", "0763951737");
		doReturn(theUser).when(userDAO).findByUsername("aymen");
		
		// Act
		User returnedUser= userService.findByUsername("aymen");
		
		userService.findByUsername("aymen");
		
		// Assert
		Assertions.assertEquals(theUser.toString(),returnedUser.toString());
		
	}
	
	@Test
	public void testSave() {
		
		// Arrange
		LocalDate date = LocalDate.of(1998, 12, 31);
		User theUser = new User("aymen", GenderType.M, date, "france", "0763951737");
		boolean thrown = false;
		
		// Act
		try {
			userService.save(theUser);
		} catch (Exception e) {
			thrown = true;
		}
		
		// Assert
		Assertions.assertFalse(thrown);
		
	}
	
	
	

	

}
