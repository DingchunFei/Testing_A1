package com.fei.myException;

import com.fei.PassBook;

public class PassphraseTooLongException extends Exception
{
    public PassphraseTooLongException(String passphrase)
    {
        super("Passphrase should not be longer than 65535\n");
    }
}
