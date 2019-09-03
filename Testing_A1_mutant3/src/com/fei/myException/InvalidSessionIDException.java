package com.fei.myException;

public class InvalidSessionIDException extends Exception 
{
    public InvalidSessionIDException(Integer sessionID)
    {
        super("Invalid session ID: " + sessionID);
    }
}
