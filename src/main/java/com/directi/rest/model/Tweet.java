package com.directi.rest.model;

import java.util.UUID;

/**
 * Created by sarvendra.a on 11/13/2015.
 */

public class Tweet
{
    public String userid;

    public String tweetid;

    public String message;

    public Tweet(){}

    public Tweet(String userid, String message)
    {
        this.userid = userid;
        this.tweetid = UUID.randomUUID().toString();
        this.message = message;
    }

    public void setUserid(String userid)
    {
        this.userid = userid;
    }

    public String getUserid()
    {
        return this.userid;
    }

    public void setTweetid(String tweetid)
    {
        this.tweetid = tweetid;
    }

    public String getTweetid()
    {
        return this.tweetid;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return this.message;
    }
}
