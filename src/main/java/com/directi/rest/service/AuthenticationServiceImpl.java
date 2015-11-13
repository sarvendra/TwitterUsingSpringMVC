package com.directi.rest.service;

import com.directi.rest.apimodel.UserContext;
import com.directi.rest.dao.TokenDao;
import com.directi.rest.dao.UserDao;
import com.directi.rest.model.Token;
import com.directi.rest.model.User;
import com.directi.rest.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.PostConstruct;

/**
 * Service responsible for all around authentication, token checks, etc.
 */
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private ApplicationContext applicationContext;

	private final AuthenticationManager authenticationManager;
	private final TokenDao tokenDao;
    private final UserDao userDao;

	public AuthenticationServiceImpl(AuthenticationManager authenticationManager, TokenDao tokenDao, UserDao userDao)
    {
		this.authenticationManager = authenticationManager;
		this.tokenDao = tokenDao;
        this.userDao = userDao;
	}

	@PostConstruct
	public void init() {
		System.out.println(" *** AuthenticationServiceImpl.init with: " + applicationContext);
	}

	@Override
	public Token authenticate(String login, String password) {
		System.out.println(" *** AuthenticationServiceImpl.authenticate");

		// Here principal=username, credentials=password
		Authentication authentication = new UsernamePasswordAuthenticationToken(login, password);
		try {
			authentication = authenticationManager.authenticate(authentication);
			// Here principal=UserDetails (UserContext in our case), credentials=null (security reasons)
			SecurityContextHolder.getContext().setAuthentication(authentication);

			if (authentication.getPrincipal() != null) {
				UserDetails userContext = (UserDetails) authentication.getPrincipal();
				Token token = tokenDao.getTokenByEmail(login);
				if (token != null)
					return token;
                token = new Token(TokenUtil.CreateTokenFromEmail(userContext.getUsername()),userContext.getUsername());
                tokenDao.addToken(token);
				if (token == null) {
					return null;
				}
				return token;
			}
		} catch (AuthenticationException e) {
			System.out.println(" *** AuthenticationServiceImpl.authenticate - FAILED: " + e.toString());
		}
		return null;
	}

	@Override
	public boolean checkToken(String token) {
		System.out.println(" *** AuthenticationServiceImpl.checkToken");

		if (validateUser(token))
		{
            //String email = TokenUtil.parseEmailFromToken(token);
			String email = tokenDao.getToken(token).getEmail();
            User user = userDao.getUserByEmail(email);
            if (user == null)
                return false;
            UserDetails userDetails = new UserContext(user);
            UsernamePasswordAuthenticationToken securityToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(securityToken);
            return true;
		}
        return false;
	}

    private boolean validateUser(String token)
    {
        Token tokenObj = tokenDao.getToken(token);
        if (tokenObj == null)
        {
            return false;
        }
//        String emailFromParser = TokenUtil.parseEmailFromToken(token);
//        return emailFromParser.equals(tokenObj.getEmail());
		return true;
    }
}
