package com.directi.rest.service;

import com.directi.rest.apimodel.Tweets;
import com.directi.rest.dao.TweetDao;
import com.directi.rest.model.Tweet;

import java.util.List;

/**
 * Created by sarvendra.a on 11/13/2015.
 */

public class TweetManager
{
    private TweetDao tweetDao;

    public TweetManager(TweetDao tweetDao)
    {
        this.tweetDao = tweetDao;
    }

    public void postTweet(String userid, String message)
    {
        Tweet tweet = new Tweet(userid, message);
        tweetDao.postTweet(tweet);
    }

    public Tweets getTweetsByUserid(String userid)
    {
        List<Tweet> tweetList = tweetDao.getTweetsByUserid(userid);
        Tweets tweets = new Tweets(tweetList);
        return tweets;
    }
}
