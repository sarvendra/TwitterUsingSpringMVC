package com.directi.rest.dao;

import com.directi.rest.model.User;

/** This is domain DAO to access users. Kinda fake here. */
public interface UserDao
{
	User getUserByEmail(String email);

	void AddUser(User user);
}
