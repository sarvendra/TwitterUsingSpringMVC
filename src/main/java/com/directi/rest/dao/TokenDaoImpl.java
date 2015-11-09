package com.directi.rest.dao;

import com.directi.rest.model.Token;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sarvendra.a on 11/6/2015.
 */
public class TokenDaoImpl implements TokenDao
{
    private JdbcTemplate jdbcTemplate;

    public TokenDaoImpl(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Token getToken(String token)
    {
        String query = "select token,email from Tokens where token=?";
        Token tokenObj;
        tokenObj = jdbcTemplate.queryForObject(query, new Object[]{token}, new TokenMapper());
        return tokenObj;
    }

    @Override
    public Token getTokenByEmail(String email)
    {
        String query = "select token,email from Tokens where email=?";
        Token tokenObj;
        tokenObj = jdbcTemplate.queryForObject(query, new Object[]{email}, new TokenMapper());
        return tokenObj;
    }

    @Override
    public void AddToken(Token token)
    {
        jdbcTemplate.update("INSERT INTO Tokens (token,email) VALUES (?, ?)",
                token.getToken(), token.getEmail());
    }

    private static final class TokenMapper implements RowMapper<Token> {
        public Token mapRow(ResultSet rs, int rowNum) throws SQLException {
            Token token = new Token();
            token.setToken(rs.getString("token"));
            token.setEmail(rs.getString("email"));
            return token;
        }
    }
}
