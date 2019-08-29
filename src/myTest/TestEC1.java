package myTest;

import java.util.List;
import java.util.ArrayList;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileSystems;

import myException.DuplicateUserException;
import myException.WeakPassphraseException;
import org.junit.*;
import program.AddUser;

import static org.junit.Assert.*;

public class TestEC1
{
    protected AddUser pb;

    //Any method annotated with "@Before" will be executed before each test,
    //allowing the tester to set up some shared resources.
    @Before public void setUp()
    {
        pb = new AddUser();
    }

    //Any method annotated with "@After" will be executed after each test,
    //allowing the tester to release any shared resources used in the setup.
    @After public void tearDown()
    {
    }

    //Any method annotation with "@Test" is executed as a test.
    @Test(expected = java.io.IOException.class)
    public void aTest()
    {
        //the assertEquals method used to check whether two values are
        //equal, using the equals method
        final int expected = 2;
        final int actual = 1 + 1;
        assertEquals(expected, actual);
    }

  /*  @Test public void anotherTest()
            throws DuplicateUserException, WeakPassphraseException
    {
        pb.addUser("passbookUsername", "properPassphrase1");

        //the assertTrue method is used to check whether something holds.
        assertTrue(pb.isUser("passbookUsername"));
        assertFalse(pb.isUser("nonUser"));
    }

    //To test an exception, specify the expected exception after the @Test
    @Test(expected = java.io.IOException.class)
    public void anExceptionTest()
            throws Throwable
    {
        throw new java.io.IOException();
    }

    //This test should fail.
    //To provide additional feedback when a test fails, an error message
    //can be included
    @Test public void aFailedTest()
    {
        //include a message for better feedback
        final int expected = 2;
        final int actual = 1 + 2;
        assertEquals("Some failure message", expected, actual);
    }*/
}
