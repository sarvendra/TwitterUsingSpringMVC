package com.directi.rest.apimodel;

/**
 * Created by sarvendra.a on 11/14/2015.
 */
public class PostTweetRequest
{
    private String message;

    public PostTweetRequest(){}

    public PostTweetRequest(String message)
    {
        this.message = message;
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
