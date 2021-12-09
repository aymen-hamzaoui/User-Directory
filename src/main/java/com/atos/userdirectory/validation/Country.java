package com.atos.userdirectory.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * New Annotation @Country to check if entry of Country is valid or not
 * Rules are defined in the CountryConstraintValidator class
 */
@Constraint(validatedBy = CountryConstraintValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Country {
	
	public String message() default "Invalid Country.";

	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};

}
