package com.directi.rest.controller;

import com.directi.rest.apimodel.AuthenticationUserToken;
import com.directi.rest.apimodel.ExternalUser;
import com.directi.rest.apimodel.RegisterUserRequest;
import com.directi.rest.apimodel.UserContext;
import com.directi.rest.service.FollowerManager;
import com.directi.rest.service.TokenManager;
import com.directi.rest.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> defaultPage()
    {
        return new ResponseEntity<>("Twitter.com", HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseEntity<ExternalUser> registerUser(@RequestBody RegisterUserRequest user)
    {
        ExternalUser externalUser = userManager.RegisterUser(user);
        return new ResponseEntity<>(externalUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<AuthenticationUserToken> login()
    {
        System.out.println(" *** Controller.login");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDetails userDetails = (UserContext)authentication.getPrincipal();
        AuthenticationUserToken authenticationUserToken = tokenManager.getAuthenticationToken(userDetails.getUsername());
        return new ResponseEntity<>(authenticationUserToken, HttpStatus.OK);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> logout()
    {
        System.out.println(" *** Controller.logout");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserContext)authentication.getPrincipal();
        tokenManager.deleteToken(userDetails.getUsername());
        return new ResponseEntity<String>("logout is successful", HttpStatus.OK);
    }

    @RequestMapping(value = "/followers", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ArrayList<String>> getFollowers()
    {
        System.out.println(" *** Controller.getFollowers");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserContext user = (UserContext)authentication.getPrincipal();
        ArrayList<String> followers = followerManager.getFollowers(user.getUserid());
        return new ResponseEntity<>(followers, HttpStatus.OK);
    }

    @RequestMapping(value = "/followers/{userid}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ArrayList<String>> getFollowersByUserid(@PathVariable String userid)
    {
        System.out.println(" *** Controller.getFollowers");
        ValidateUser(userid);

        ArrayList<String> followers = followerManager.getFollowers(userid);
        return new ResponseEntity<>(followers, HttpStatus.OK);
    }

    @RequestMapping(value = "/following", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ArrayList<String>> getFollowing()
    {
        System.out.println(" *** Controller.getFollowing");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserContext user = (UserContext)authentication.getPrincipal();
        ArrayList<String> following = followerManager.getFollowing(user.getUserid());
        return new ResponseEntity<>(following, HttpStatus.OK);
    }

    @RequestMapping(value = "/following/{userid}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ArrayList<String>> getFollowingByUserid(@PathVariable String userid)
    {
        System.out.println(" *** Controller.getFollowingByUserid");
        ValidateUser(userid);

        ArrayList<String> following = followerManager.getFollowing(userid);
        return new ResponseEntity(following, HttpStatus.OK);
    }

    @RequestMapping(value = "/addfollowing/{followingid}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> addFollowing(@PathVariable String followingid)
    {
        System.out.println(" *** Controller.addFollowing");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserContext user = (UserContext)authentication.getPrincipal();

        ValidateUser(followingid);

        followerManager.addFollower(followingid, user.getUserid());
        String message = user.getUserid() + " following " + followingid;
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    private void ValidateUser(String userid)
    {
        userManager.ValidateUser(userid);
    }
}
