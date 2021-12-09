package com.atos.userdirectory.rest;

public class UserNotFrenshResidentException extends RuntimeException {

	/**
	 * Exception for user is not frensh resident
	 */
	private static final long serialVersionUID = 1317104206941684308L;

	public UserNotFrenshResidentException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotFrenshResidentException(String message) {
		super(message);
	}

	public UserNotFrenshResidentException(Throwable cause) {
		super(cause);
	}

}
