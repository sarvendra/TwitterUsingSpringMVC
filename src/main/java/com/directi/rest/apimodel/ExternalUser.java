package com.directi.rest.apimodel;

import com.directi.rest.model.User;

/**
 * Created by sarvendra.a on 11/3/2015.
 */
public class ExternalUser
{
    private String userid;
    private String name;
    private String email;

    public ExternalUser() {}

    public ExternalUser(User user) {
        this.userid = user.getUserid();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public String getUserId() {
        return userid;
    }

    public void setUserId(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
