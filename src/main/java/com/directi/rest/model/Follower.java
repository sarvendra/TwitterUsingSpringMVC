package com.directi.rest.model;

/**
 * Created by sarvendra.a on 11/14/2015.
 */
public class Follower
{
    private String userid;
    private String followerid;

    public Follower(){}

    public Follower(String userid, String followerid)
    {
        this.userid = userid;
        this.followerid = followerid;
    }

    public void setUserid(String userid)
    {
        this.userid = userid;
    }

    public String getUserid()
    {
        return this.userid;
    }

    public void setFollowerid(String followerid)
    {
        this.followerid = followerid;
    }

    public String getFollowerid()
    {
        return this.followerid;
    }
}
