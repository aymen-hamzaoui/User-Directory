package com.atos.userdirectory.service;

import com.atos.userdirectory.entity.User;

public interface UserService {

	public void save(User theUser);
	
	public User findByUsername(String theUsername);
	
	public User customizeUser(User theUser);
	
	public String customizeCountry(String country);
	
	public void checkValidUser(User theUser);
}
