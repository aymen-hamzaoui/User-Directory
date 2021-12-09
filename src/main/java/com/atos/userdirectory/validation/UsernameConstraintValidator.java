package com.atos.userdirectory.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 
 * Rules to validate the @Username Entries
 *
 */
public class UsernameConstraintValidator implements ConstraintValidator<Username,String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		// username's size must be between 6 and 45 characters
		if(value.trim().length()>=6 && value.trim().length()<45)
			return true;
		
		return false;
	}

}
