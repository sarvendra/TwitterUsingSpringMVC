package com.directi.rest.apimodel;

/**
 * Created by sarvendra.a on 11/3/2015.
 */
public class AuthenticationUserToken
{
    private String email;
    private String token;

    public AuthenticationUserToken(){}

    public AuthenticationUserToken(String email, String sessionToken) {
        this.email = email;
        this.token = sessionToken;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
