package com.atos.userdirectory.rest;

public class UserAlreadyExistsException extends RuntimeException {

	/**
	 * Exception for existant User in the DB
	 */

	private static final long serialVersionUID = 337107840121431979L;

	public UserAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAlreadyExistsException(String message) {
		super(message);
	}

	public UserAlreadyExistsException(Throwable cause) {
		super(cause);
	}

}
