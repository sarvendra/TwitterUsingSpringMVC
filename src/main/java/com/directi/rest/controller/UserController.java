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

import javax.servlet.http.HttpServletResponse;

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
    public ExternalUser registerUser(@RequestBody RegisterUserRequest user, HttpServletResponse response)
    {
        ExternalUser externalUser = userManager.RegisterUser(user);
        return externalUser;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public AuthenticationUserToken login()
    {
        System.out.println(" *** MainRestController.login");
        Authentication authentication = (Authentication)SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserContext)authentication.getPrincipal();
        if (userDetails != null)
        {
            AuthenticationUserToken authenticationUserToken = tokenManager.GetAuthenticationToken(userDetails.getUsername());
            return authenticationUserToken;
        }
        return null;
    }
}
