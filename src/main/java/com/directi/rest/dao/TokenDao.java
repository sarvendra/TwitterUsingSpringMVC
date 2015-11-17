package com.directi.rest.dao;

import com.directi.rest.model.Token;

/**
 * Created by sarvendra.a on 11/6/2015.
 */
public interface TokenDao
{
    public Token getToken(String token);

    public void addToken(Token token);

    public Token getTokenByEmail(String email);

    void deleteToken(String email);
}
