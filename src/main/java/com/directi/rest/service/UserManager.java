package com.directi.rest.service;

import com.directi.rest.Exception.DuplicateUserException;
import com.directi.rest.Exception.InvalidEntityException;
import com.directi.rest.apimodel.ExternalUser;
import com.directi.rest.apimodel.RegisterUserRequest;
import com.directi.rest.dao.UserDao;
import com.directi.rest.model.User;

/**
 * Created by sarvendra.a on 11/6/2015.
 */
public class UserManager
{
    private UserDao userDao;

    public UserManager(UserDao userDao)
    {
        this.userDao = userDao;
    }

    public ExternalUser RegisterUser(RegisterUserRequest user)
    {
        User searchedUser = userDao.getUserByEmail(user.getEmail());
        if (searchedUser != null)
        {
            throw new DuplicateUserException();
        }

        User newUser = createUser(user);
        userDao.AddUser(newUser);
        ExternalUser externalUser = new ExternalUser(newUser);
        return externalUser;
    }

    public void ValidateUser(String userid)
    {
        User user = userDao.getUserByUserid(userid);
        if (user == null)
        {
            throw new InvalidEntityException();
        }
    }

    private User createUser(RegisterUserRequest user)
    {
        User newUser = new User(user.getName(),user.getEmail(), user.getPassword());
        return newUser;
    }
}
