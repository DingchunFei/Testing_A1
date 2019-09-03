package com.fei.myException;

public class AlreadyLoggedInException extends Exception 
{
    public AlreadyLoggedInException(String username)
    {
        super("Username already logged in: " + username);
    }
}
