package com.directi.rest.controller;

import com.directi.rest.apimodel.PostTweetRequest;
import com.directi.rest.apimodel.Tweets;
import com.directi.rest.apimodel.UserContext;
import com.directi.rest.service.TweetManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sarvendra.a on 11/13/2015.
 */

@Controller
public class TweetController extends BaseController
{
    @Autowired
    private TweetManager tweetManager;

    @RequestMapping(value = "/tweets", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Tweets> getTweets()
    {
        System.out.println(" *** Controller.getTweets");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserContext user = (UserContext)authentication.getPrincipal();
        Tweets tweets = tweetManager.getTweetsByUserid(user.getUserid());
        return new ResponseEntity<>(tweets, HttpStatus.OK);
    }

    @RequestMapping(value = "/tweets/{userid}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Tweets> getTweetsByUserid(@PathVariable String userid)
    {
        System.out.println(" *** Controller.getTweetsByUserid");
        Tweets tweets = tweetManager.getTweetsByUserid(userid);
        return new ResponseEntity<>(tweets, HttpStatus.OK);
    }

    @RequestMapping(value = "/posttweet", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> postTweet(@RequestBody PostTweetRequest tweetRequest)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserContext user = (UserContext)authentication.getPrincipal();
        boolean isTweetAdded = tweetManager.postTweet(user.getUserid(), tweetRequest.getMessage());
        String message = "Unable to post tweet";
        if (isTweetAdded)
        {
            message = "tweet posted";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
