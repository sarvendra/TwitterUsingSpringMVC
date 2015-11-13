package com.directi.rest.dao;

import com.directi.rest.model.Tweet;

import java.util.List;

/**
 * Created by sarvendra.a on 11/13/2015.
 */
public interface TweetDao
{
    public List<Tweet> getTweetsByUserid(String userid);

    public void postTweet(Tweet tweet);
}
