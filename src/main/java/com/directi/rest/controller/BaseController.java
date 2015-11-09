package com.directi.rest.controller;

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
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handleServerException(EmptyResultDataAccessException e) {
        String message = "Page not found";
        return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CannotGetJdbcConnectionException.class)
    public ResponseEntity<String> handleNoConnection(CannotGetJdbcConnectionException e) {
        String message = "Unable to connect to the database. Is it running?";
        return new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

