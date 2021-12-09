package com.atos.userdirectory.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.atos.userdirectory.service.UserService;
import com.neovisionaries.i18n.CountryCode;

/**
 * 
 * Rules to validate the @Country Entries
 *
 */
@Component
public class CountryConstraintValidator implements ConstraintValidator<Country, String> {


	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		value = customizeCountryName(value);
		
		// checking if country code exists in the CountryCode emported Enum
		for (CountryCode code : CountryCode.values()) {

			if ((code.getName() != null && code.getName().equals(value))
					|| (code.getAlpha2() != null && code.getAlpha2().equals(value))
					|| (code.getAlpha3() != null && code.getAlpha3().equals(value))) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 
	 * @param country string
	 * @return country string trimmed and customized according to it s length
	 */
	private String customizeCountryName(String value) {
		if (value != null) {
			
			value = value.trim();

			if (value.length() <= 3)
				
				value = value.toUpperCase();
			
			else {
				// Only first character is in UpperCase
				value = StringUtils.capitalize(value.toLowerCase());
			}
		}

		return value;
	}

}
