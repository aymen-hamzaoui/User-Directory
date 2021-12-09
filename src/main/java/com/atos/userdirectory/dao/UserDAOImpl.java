package com.atos.userdirectory.dao;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atos.userdirectory.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	/**
	 * Class -Repository- that ensures the communication with the database
	 */

	@Autowired
	private EntityManager entityManager;

	private Session currentSession;

	@PostConstruct
	public void init() {

		// Get current hibernate session
		currentSession = entityManager.unwrap(Session.class);
	}

	/**
	 * Save a user
	 * 
	 * @param User object
	 * @return void
	 */
	@Override
	public void save(User theUser) {

		currentSession.persist(theUser);
	}

	/**
	 * Find user by username
	 * 
	 * @param String username
	 * @return the User if found / null if not found
	 */
	@Override
	public User findByUsername(String theUsername) {

		User theUser = currentSession.get(User.class, theUsername);

		return theUser;
	}

}
