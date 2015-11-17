package com.directi.rest.Exception;

import org.springframework.http.HttpStatus;

/**
 * Created by sarvendra.a on 11/17/2015.
 */
public class DuplicateUserException extends BaseAppException
{
    public DuplicateUserException()
    {
        super("User with this email id already exists", HttpStatus.NOT_ACCEPTABLE);
    }
}
