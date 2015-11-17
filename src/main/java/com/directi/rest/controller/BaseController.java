package com.directi.rest.controller;

import com.directi.rest.Exception.DuplicateUserException;
import com.directi.rest.Exception.InvalidEntityException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by sarvendra.a on 11/5/2015.
 */

public abstract class BaseController
{
    @ExceptionHandler(CannotGetJdbcConnectionException.class)
    public ResponseEntity<String> handleNoJdbcConnectionException(CannotGetJdbcConnectionException e)
    {
        String message = "Cannot connect to database";
        return new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<String> handleDuplicateUserException(DuplicateUserException e)
    {
        return new ResponseEntity<String>(e.getErrMessage(), e.getErrCode());
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleCommonDatabaseException(DataAccessException e)
    {
        return new ResponseEntity<>("Internal database error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<String> handleInvalidEntityException(InvalidEntityException e)
    {
        return new ResponseEntity<String>(e.getErrMessage(), e.getErrCode());
    }
}

