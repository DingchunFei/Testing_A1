package com.fei.myException;

import java.net.URL;

public class NoSuchURLException extends Exception
{
    public NoSuchURLException (String username, URL url)
    {
        super("User " + username + " does not have password for URL " + url.toString());
    }
}
