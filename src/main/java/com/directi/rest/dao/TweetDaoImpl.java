package com.directi.rest.dao;

import com.directi.rest.model.Tweet;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by sarvendra.a on 11/13/2015.
 */
public class TweetDaoImpl implements TweetDao
{
    private JdbcTemplate jdbcTemplate;

    public TweetDaoImpl(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Tweet> getTweetsByUserid(String userid)
    {
        String query = "select tweetid,userid,message from Tweets where userid=?" + userid;
        List<Tweet> tweets = null;
        try
        {
            tweets = jdbcTemplate.query(query, new BeanPropertyRowMapper(Tweet.class));
        }
        catch (Exception exp)
        {
            System.out.println("Exception: "+ exp.getMessage());
        }
        return tweets;
    }

    @Override
    public void postTweet(Tweet tweet)
    {
        jdbcTemplate.update("INSERT INTO Tweets (tweetid,userid,message) VALUES (?, ?, ?)",
                tweet.getTweetid(), tweet.getUserid(), tweet.getMessage());
    }
}
