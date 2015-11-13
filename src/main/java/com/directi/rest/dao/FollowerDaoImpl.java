package com.directi.rest.dao;

import com.directi.rest.model.Follower;
import com.directi.rest.model.Tweet;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by sarvendra.a on 11/14/2015.
 */
public class FollowerDaoImpl implements FollowerDao
{
    private JdbcTemplate jdbcTemplate;

    public FollowerDaoImpl(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Follower> getFollowersList(String userid)
    {
        String query = "select userid,followerid from Followers where userid='" + userid + "'";
        List<Follower> followers = null;
        try
        {
            followers = jdbcTemplate.query(query, new BeanPropertyRowMapper(Follower.class));
        }
        catch (Exception exp)
        {
            System.out.println("Exception: "+ exp.getMessage());
        }
        return followers;
    }

    @Override
    public List<Follower> getFollowingList(String userid)
    {
        String query = "select userid,followerid from Followers where followerid='" + userid + "'";
        List<Follower> followers = null;
        try
        {
            followers = jdbcTemplate.query(query, new BeanPropertyRowMapper(Tweet.class));
        }
        catch (Exception exp)
        {
            System.out.println("Exception: "+ exp.getMessage());
        }
        return followers;
    }

    @Override
    public boolean addFollower(String userid, String followerid)
    {
        boolean isFollowerAdded = true;
        try
        {
            jdbcTemplate.update("INSERT INTO Followers (userid,followerid) VALUES (?, ?)",
                    userid, followerid);
        }
        catch (Exception exp)
        {
            isFollowerAdded = false;
            System.out.println("Exception: " + exp.getMessage());
        }
        return isFollowerAdded;
    }
}
