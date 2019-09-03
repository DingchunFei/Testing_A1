package com.fei.myException;

public class IncorrectPassphraseException extends Exception 
{
    public IncorrectPassphraseException(String username, String passphrase)
    {
        super("Incorrect passphrase: " + passphrase + " for user " + username);
    }
}
