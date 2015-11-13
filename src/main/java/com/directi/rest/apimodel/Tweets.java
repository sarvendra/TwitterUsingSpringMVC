package com.directi.rest.apimodel;

import com.directi.rest.model.Tweet;

import java.util.List;

/**
 * Created by sarvendra.a on 11/14/2015.
 */
public class Tweets
{
    public List<Tweet> tweets;

    public Tweets(List<Tweet> tweets)
    {
        this.tweets = tweets;
    }

    public void setTweets(List<Tweet> tweets)
    {
        this.tweets = tweets;
    }

    public List<Tweet> getTweets()
    {
        return this.tweets;
    }
}
