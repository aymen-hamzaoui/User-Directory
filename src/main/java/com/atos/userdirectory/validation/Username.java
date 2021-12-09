package com.atos.userdirectory.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * New Annotation @Username to check if entry of Country is valid or not
 * Rules are defined in the UsernameConstraintValidator class
 */
@Constraint(validatedBy = UsernameConstraintValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Username {
	
	public String message() default "Invalid Username.";

	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};
}
 