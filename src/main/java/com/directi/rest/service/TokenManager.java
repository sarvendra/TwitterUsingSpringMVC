package com.directi.rest.service;

import com.directi.rest.apimodel.AuthenticationUserToken;
import com.directi.rest.dao.TokenDao;
import com.directi.rest.model.Token;

/**
 * Created by sarvendra.a on 11/6/2015.
 */
public class TokenManager
{
    private TokenDao tokenDao;

    public TokenManager(TokenDao tokenDao)
    {
        this.tokenDao = tokenDao;
    }

    public AuthenticationUserToken getAuthenticationToken(String email)
    {
        Token token = tokenDao.getTokenByEmail(email);
        if (token == null)
        {
            return null;
        }
        AuthenticationUserToken authenticationUserToken = new AuthenticationUserToken(token.getEmail(), token.getToken());
        return authenticationUserToken;
    }

    public void deleteToken(String email)
    {
        tokenDao.deleteToken(email);
    }
}
