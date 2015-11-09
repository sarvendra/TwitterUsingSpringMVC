package com.directi.rest.controller;

import com.directi.rest.apimodel.AuthenticationUserToken;
import com.directi.rest.apimodel.ExternalUser;
import com.directi.rest.apimodel.RegisterUserRequest;
import com.directi.rest.apimodel.UserContext;
import com.directi.rest.service.TokenManager;
import com.directi.rest.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
