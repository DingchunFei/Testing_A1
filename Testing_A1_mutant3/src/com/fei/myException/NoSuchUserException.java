package com.fei.myException;

public class NoSuchUserException extends Exception
{
    public NoSuchUserException (String username)
    {
        super("Username does not exist: " + username);
    }
}
