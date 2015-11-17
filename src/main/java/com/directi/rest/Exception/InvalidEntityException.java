package com.directi.rest.Exception;

import org.springframework.http.HttpStatus;

/**
 * Created by sarvendra.a on 11/17/2015.
 */
public class InvalidEntityException extends BaseAppException
{
    public InvalidEntityException()
    {
        super("invalid data", HttpStatus.BAD_REQUEST);
    }
}
