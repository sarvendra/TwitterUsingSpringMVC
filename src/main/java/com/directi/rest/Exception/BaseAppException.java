package com.directi.rest.Exception;

import org.springframework.http.HttpStatus;

/**
 * Created by sarvendra.a on 11/16/2015.
 */

public class BaseAppException extends RuntimeException
{
    private String errMessage;
    private HttpStatus errCode;

    public BaseAppException(String errMessage, HttpStatus errCode)
    {
        this.errMessage = errMessage;
        this.errCode = errCode;
    }

    public void setErrMessage(String errMessage)
    {
        this.errMessage = errMessage;
    }

    public String getErrMessage()
    {
        return this.errMessage;
    }

    public void setErrCode(HttpStatus errCode)
    {
        this.errCode = errCode;
    }

    public HttpStatus getErrCode()
    {
        return this.errCode;
    }
}
