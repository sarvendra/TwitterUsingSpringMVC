package com.directi.rest.dao;

import com.directi.rest.model.User;

public interface UserDao
{
	User getUserByEmail(String email);

	User getUserByUserid(String userid);

	void AddUser(User user);
}
