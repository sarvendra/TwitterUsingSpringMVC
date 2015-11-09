package com.directi.rest.service;

import com.directi.rest.model.Token;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

	/**
	 * Authenticates the user and returns valid token. If anything fails, {@code null} is returned instead.
	 * Prepares {@link org.springframework.security.core.context.SecurityContext} if authentication succeeded.
	 */
	Token authenticate(String login, String password);

	/**
	 * Checks the authentication token and if it is valid prepares
	 * {@link org.springframework.security.core.context.SecurityContext} and returns true.
	 */
	boolean checkToken(String token);
}
