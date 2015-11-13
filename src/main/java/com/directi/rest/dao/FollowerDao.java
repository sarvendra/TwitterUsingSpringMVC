package com.directi.rest.dao;

import com.directi.rest.model.Follower;

import java.util.List;

/**
 * Created by sarvendra.a on 11/14/2015.
 */
public interface FollowerDao
{
    public List<Follower> getFollowersList(String userid);

    public List<Follower> getFollowingList(String userid);

    public boolean addFollower(String userid, String followerid);
}
