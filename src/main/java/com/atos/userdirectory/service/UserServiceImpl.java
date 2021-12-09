package com.atos.userdirectory.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.atos.userdirectory.dao.UserDAO;
import com.atos.userdirectory.entity.User;
import com.atos.userdirectory.rest.UserNotAdultException;
import com.atos.userdirectory.rest.UserNotFrenshResidentException;
import com.neovisionaries.i18n.CountryCode;

@Service
public class UserServiceImpl implements UserService {
	
	// field dependency injection
	@Autowired
	private UserDAO userDAO;

	// Transactional for handling commit after session closed
	@Override
	@Transactional
	public void save(User theUser) {
		
		// customize the username field and the country field
		theUser = customizeUser(theUser);
		
		// check for age and residence constraint
		checkValidUser(theUser);

		userDAO.save(theUser);
	}

	@Override
	@Transactional
	public User findByUsername(String theUsername) {

		return userDAO.findByUsername(theUsername);
	}
	

	/**
	 * @param User object
	 * @return Same User object with username trimmed and country set to alpha-3 code
	 */
	public User customizeUser(User theUser) {

		theUser.setUsername(theUser.getUsername().trim());
		
		theUser.setCountry(customizeCountry(theUser.getCountry()));
		
		return theUser;
	}
	
	/**
	 * @param country string
	 * @return country trimmed and if it exists: it is  converted to alpha-3 code
	 */
	public String customizeCountry(String country) {

		if(country!=null) {
			
			country = country.trim();

			if (country.length() <= 3)
				
				country = country.toUpperCase();
			
			else {
				
				country = StringUtils.capitalize(country.toLowerCase());
			}			
		}

		
		for (CountryCode code : CountryCode.values()) {

			if ((code.getName() != null && code.getName().equals(country))
					|| (code.getAlpha2() != null && code.getAlpha2().equals(country))
					|| (code.getAlpha3() != null && code.getAlpha3().equals(country))) {

				return code.getAlpha3();
			}
		}
			
		return country;
	}
	
	/**
	 * Check if user is adult and frensh resident
	 * @param User
	 */
	public void checkValidUser(User theUser) {
		
		int age=Period.between(theUser.getBirthDate(), LocalDate.now()).getYears();
		
		if(age<18) {
			throw new UserNotAdultException("The user you're trying to add is not an adult."
					+ " We cannot proceed with this operation");
		}
						
		if(!theUser.getCountry().equals("FRA")) {
			throw new UserNotFrenshResidentException("The user you're trying to add is not a French resident."
					+ " We cannot proceed with this operation.");
		}		
				
	}
	
	
	
	
	
	
}
