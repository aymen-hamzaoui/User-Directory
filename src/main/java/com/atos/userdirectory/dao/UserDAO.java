package com.atos.userdirectory.dao;



import com.atos.userdirectory.entity.User;

public interface UserDAO {

	public void save(User theUser);
	
	public User findByUsername(String theUsername);
}
