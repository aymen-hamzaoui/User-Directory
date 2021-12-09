package com.atos.userdirectory.rest;

public class UserNotFoundException extends RuntimeException {

	/**
	 * Exception for user not found
	 */
	private static final long serialVersionUID = 7379523282461974207L;

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
	}

}
