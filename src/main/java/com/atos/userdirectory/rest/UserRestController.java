package com.atos.userdirectory.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atos.userdirectory.entity.User;
import com.atos.userdirectory.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value = "User API")
public class UserRestController {

	/**
	 * User Controller
	 */

	// Field dependency injection

	@Autowired
	private UserService userService;

	// Customize infos for Swagger Documentation

	@ApiOperation(value = "Add a new User")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User successfully added"),
			@ApiResponse(code = 409, message = "Username already exists"),
			@ApiResponse(code = 400, message = "Invalid request body"), })
	@PostMapping("/users")
	public User addUser(@Valid @RequestBody User theUser) {

		// Check if user already exists
		User tempUser = userService.findByUsername(theUser.getUsername());

		// if exists, then:
		if (tempUser != null)
			throw new UserAlreadyExistsException("Username - " + theUser.getUsername() + " - already exists!");

		// if not, then:
		userService.save(theUser);

		return theUser;
	}

	@ApiOperation(value = "Find User by his Username")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User Found"),
			@ApiResponse(code = 404, message = "User Not Found") })
	@GetMapping("/users/{theUsername}")
	public User findByUsername(@PathVariable String theUsername) {

		// Call service layer to find the user
		User theUser = userService.findByUsername(theUsername);

		// if null, it means user is not found:
		if (theUser == null)
			throw new UserNotFoundException("Username - " + theUsername + " - not found!");

		// if found, then:
		return theUser;

	}

}
