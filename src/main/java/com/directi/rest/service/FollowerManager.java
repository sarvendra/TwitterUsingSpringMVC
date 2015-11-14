package com.directi.rest.service;

import com.directi.rest.apimodel.Tweets;
import com.directi.rest.dao.FollowerDao;
import com.directi.rest.model.Follower;
import com.directi.rest.model.Tweet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sarvendra.a on 11/14/2015.
 */

public class FollowerManager
{
    private FollowerDao followerDao;

    public FollowerManager(FollowerDao followerDao)
    {
        this.followerDao = followerDao;
    }

    public ArrayList<String> getFollowers(String userid)
    {
        List<Follower> followersList = followerDao.getFollowersList(userid);
        ArrayList<String> followers = new ArrayList<>();
        for(Follower follower: followersList)
        {
            followers.add(follower.getFollowerid());
        }
        return followers;
    }

    public ArrayList<String> getFollowing(String userid)
    {
        List<Follower> followingList = followerDao.getFollowingList(userid);
        ArrayList<String> following = new ArrayList<String>();
        for(Follower follower: followingList)
        {
            following.add(follower.getUserid());
        }
        return following;
    }

    public boolean addFollower(String userid, String followerid)
    {
        return followerDao.addFollower(userid,followerid);
    }
}
