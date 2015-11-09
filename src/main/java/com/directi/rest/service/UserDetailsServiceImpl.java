package com.directi.rest.service;

import com.directi.rest.apimodel.UserContext;
import com.directi.rest.dao.UserDao;
import com.directi.rest.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Implements Spring Security {@link UserDetailsService} that is injected into authentication provider in configuration XML.
 * It interacts with domain, loads user details and wraps it into {@link UserContext} which implements Spring Security {@link UserDetails}.
 */
public class UserDetailsServiceImpl implements UserDetailsService
{
	private final UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao)
    {
        this.userDao = userDao;
    }

	/**
	 * This will be called from
	 * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider#retrieveUser(String, org.springframework.security.authentication.UsernamePasswordAuthenticationToken)}
	 * when {@link AuthenticationService#authenticate(String, String)} calls
	 * {@link AuthenticationManager#authenticate(org.springframework.security.core.Authentication)}. Easy.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		System.out.println(" *** UserDetailsServiceImpl.loadUserByUsername");
		User user = userDao.getUserByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User " + username + " not found");
		}
		return new UserContext(user);
	}
}
