package com.directi.rest.controller;

import com.directi.rest.apimodel.AuthenticationUserToken;
import com.directi.rest.apimodel.ExternalUser;
import com.directi.rest.apimodel.RegisterUserRequest;
import com.directi.rest.apimodel.UserContext;
import com.directi.rest.service.FollowerManager;
import com.directi.rest.service.TokenManager;
import com.directi.rest.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by sarvendra.a on 11/5/2015.
 */

@Controller
public class UserController extends BaseController
{
    @Autowired
    private UserManager userManager;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private FollowerManager followerManager;

    @RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
    @ResponseBody
    public String defaultPage()
    {
        return "Twitter.com";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ExternalUser registerUser(@RequestBody RegisterUserRequest user)
    {
        ExternalUser externalUser = userManager.RegisterUser(user);
        return externalUser;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public AuthenticationUserToken login()
    {
        System.out.println(" *** Controller.login");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserContext)authentication.getPrincipal();
        if (userDetails != null)
        {
            AuthenticationUserToken authenticationUserToken = tokenManager.getAuthenticationToken(userDetails.getUsername());
            return authenticationUserToken;
        }
        return new AuthenticationUserToken("null", "null");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public String logout()
    {
        System.out.println(" *** Controller.logout");
        String logoutMessage = "logout is unsuccessful";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserContext)authentication.getPrincipal();
        if (userDetails != null)
        {
            if (tokenManager.deleteToken(userDetails.getUsername()))
            {
                logoutMessage = "logout is successful";
            }
        }
        return logoutMessage;
    }

    @RequestMapping(value = "/followers", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<String> getFollowers()
    {
        System.out.println(" *** Controller.getFollowers");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserContext user = (UserContext)authentication.getPrincipal();
        ArrayList<String> followers = null;
        if (user != null)
        {
            followers = followerManager.getFollowers(user.getUserid());
        }
        return followers;
    }

    @RequestMapping(value = "/followers/{userid}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<String> getFollowersByUserid(@PathVariable String userid)
    {
        System.out.println(" *** Controller.getFollowers");
        ArrayList<String> followers = followerManager.getFollowers(userid);
        return followers;
    }

    @RequestMapping(value = "/following", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<String> getFollowing()
    {
        System.out.println(" *** Controller.getFollowing");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserContext user = (UserContext)authentication.getPrincipal();
        ArrayList<String> following = null;
        if (user != null)
        {
            following = followerManager.getFollowing(user.getUserid());
        }
        return following;
    }

    @RequestMapping(value = "/following/{userid}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<String> getFollowingByUserid(@PathVariable String userid)
    {
        System.out.println(" *** Controller.getFollowingByUserid");
        ArrayList<String> following = followerManager.getFollowing(userid);
        return following;
    }

    @RequestMapping(value = "/addfollowing/{followingid}", method = RequestMethod.GET)
    @ResponseBody
    public String addFollowing(@PathVariable String followingid)
    {
        System.out.println(" *** Controller.addFollowing");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserContext user = (UserContext)authentication.getPrincipal();
        String message = "Unable to follow";
        if (followerManager.addFollower(followingid, user.getUserid()))
        {
            message = user.getUserid() + " following " + followingid;
        }
        return message;
    }
}
