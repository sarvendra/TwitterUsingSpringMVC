package com.directi.rest.dao;

import com.directi.rest.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sarvendra.a on 11/6/2015.
 */
public class UserDaoImpl implements UserDao
{
    private JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserByEmail(String email)
    {
        String query = "select userid,name,email,password,role from Users where email=?";
        User user = null;
        try
        {
            user = jdbcTemplate.queryForObject(query, new Object[]{email}, new UserMapper());
        }
        catch (Exception exp)
        {
            System.out.println("Exception thrown in querying UserDao getUserByEmail");
        }
        return user;
    }

    @Override
    public void AddUser(User user)
    {
        jdbcTemplate.update("INSERT INTO Users (userid, name, email, password, role) VALUES (?, ?, ?, ?, ?)",
                user.getUserid(), user.getName(), user.getEmail(), user.getPassword(), user.getRole());
    }

    private static final class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            User user = new User();
            user.setUserid(rs.getString("userid"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            return user;
        }
    }
}
