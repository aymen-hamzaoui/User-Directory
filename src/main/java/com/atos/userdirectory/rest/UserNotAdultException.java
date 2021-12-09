package com.atos.userdirectory.rest;

public class UserNotAdultException extends RuntimeException {

	/**
	 * Exception for User not adult
	 */

	private static final long serialVersionUID = -1856958967157834559L;

	public UserNotAdultException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotAdultException(String message) {
		super(message);
	}

	public UserNotAdultException(Throwable cause) {
		super(cause);
	}

}
