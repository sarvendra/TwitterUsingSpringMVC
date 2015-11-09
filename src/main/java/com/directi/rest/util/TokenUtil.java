package com.directi.rest.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Created by sarvendra.a on 11/6/2015.
 */
public class TokenUtil
{
    private static final String secret = "TwitterUsingSpringMVC";

    public static String parseEmailFromToken(String token) {
        String email = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return email;
    }

    public static String CreateTokenFromEmail(String email)
    {
        return Jwts.builder()
                .setSubject(email)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
