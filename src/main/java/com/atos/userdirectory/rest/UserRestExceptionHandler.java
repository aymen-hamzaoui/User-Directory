package com.atos.userdirectory.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRestExceptionHandler {
	
	/**
	 * AOP Proxy: controller that allows monitoring of the different methods 
	 * of the different layers: controller, service, repository*
	 * 
	 */

	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(UserNotFoundException exc) {

		UserErrorResponse error = new UserErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(),
				System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(HttpMessageNotReadableException exc) {

		String message = exc.getMessage();

		if (exc.getMessage().contains("GenderType")) {
			
			// Gender constraint
			message = "The 'gender' field must be one of the following values: [FEMALE, M, F, MALE] , or null.";
			
		} else if (exc.getMessage().contains("birthDate")) {
				
			// BirthDate constraint
				message = "The 'birthDate' field is required, must respect the following format [yyyy-MM-dd], and must be a past date.";

			}

		// create error instance
		UserErrorResponse error = new UserErrorResponse(HttpStatus.BAD_REQUEST.value(), message,
				System.currentTimeMillis());
		
		// return reponse as json
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(UserAlreadyExistsException exc) {

		UserErrorResponse error = new UserErrorResponse(HttpStatus.CONFLICT.value(), exc.getMessage(),
				System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(MethodArgumentNotValidException exc) {

		String message = exc.getMessage();

		String errorField = exc.getFieldError().getField();

		if (errorField.equals("username")) {

			// username constraint
			message = "The 'username' field is required, and must be at least 6 characters long.";

		} else if (errorField.equals("birthDate")) {
			
			// birthDate constraint
			message = "The 'birthDate' field is required, must respect the following format [yyyy-MM-dd], and must be a past date.";

		} else if (errorField.equals("country")) {

			// country constraint
			message = "The 'country' field is required and must be: Country name, ISO 3166-1 alpha-2 code or ISO 3166-1 alpha-3 code";

		} else if (errorField.equals("phone")) {

			// phone constraint
			message = "The 'phone' field must be 10 numeric characters long, or null. Don't forget the double quotes!";

		}

		UserErrorResponse error = new UserErrorResponse(HttpStatus.BAD_REQUEST.value(), message,
				System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(UserNotAdultException exc) {

		UserErrorResponse error = new UserErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(),
				System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(UserNotFrenshResidentException exc) {
		
		UserErrorResponse error = new UserErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(),
				System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	// Generic Exception handling
	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(Exception exc) {

		UserErrorResponse error = new UserErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(),
				System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
