package com.directi.rest.model;

/**
 * Created by sarvendra.a on 11/6/2015.
 */
public class Token
{
    private String token;
    private String email;

    public Token(){}

    public Token(String token, String email)
    {
        this.token = token;
        this.email = email;
    }

    public String getToken()
    {
        return this.token;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

}
