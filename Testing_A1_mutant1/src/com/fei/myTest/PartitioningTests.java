package com.fei.myTest;

import com.fei.PassBook;
import com.fei.myException.*;
import org.junit.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import static org.junit.Assert.*;


public class PartitioningTests
{
    protected PassBook pb;

    //Any method annotated with "@Before" will be executed before each test,
    //allowing the tester to set up some shared resources.
    @Before public void setUp() throws WeakPassphraseException, DuplicateUserException, NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException {
        pb = new PassBook();
        pb.addUser("Jessica","1234ABCDabcd");
        pb.addUser("Chris","Chris123");
        pb.addUser("Mary","abcd1234ABCD");
    }

/*    @Test
    public void test_EC1() {
        //
    }*/
    /**
     * Test addUser()
     */
    @Test(expected = DuplicateUserException.class)
    public void test_EC1() throws WeakPassphraseException, DuplicateUserException {
        pb.addUser("Jessica","1234ABCDabcd");
    }

    @Test(expected = WeakPassphraseException.class)
    public void test_EC2() throws WeakPassphraseException, DuplicateUserException {
        pb.addUser("Bob","12ABabc");
    }

    @Test(expected = WeakPassphraseException.class)
    public void test_EC3() throws WeakPassphraseException, DuplicateUserException {
        pb.addUser("Bob","1234ABCD");
    }

    @Test(expected = WeakPassphraseException.class)
    public void test_EC4() throws WeakPassphraseException, DuplicateUserException {
        pb.addUser("Bob","1234abcd");
    }

    @Test(expected = WeakPassphraseException.class)
    public void test_EC5() throws WeakPassphraseException, DuplicateUserException {
        pb.addUser("Bob","ABCDabcd");
    }

    @Test
    public void test_EC6() throws WeakPassphraseException, DuplicateUserException {
        pb.addUser("Bob","ABCDabcd1234");
    }

    /**
     * Test loginUser()
     */
    @Test(expected = NoSuchUserException.class)
    public void test_EC7() throws NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException {
        pb.loginUser("Deon","12CDab34ABcd");
    }

    @Test(expected = AlreadyLoggedInException.class)
    public void test_EC8() throws NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException {
        pb.loginUser("Mary","abcd1234ABCD");
        pb.loginUser("Mary","abcd1234ABCD");
    }

    @Test(expected = IncorrectPassphraseException.class)
    public void test_EC9() throws NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException {
        pb.loginUser("Chris","AasdDd1234");
    }

    @Test
    public void test_EC10() throws NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException {
        pb.loginUser("Chris","Chris123");
    }

    /**
     * Test updateDetail()
     */
    @Test(expected = InvalidSessionIDException.class)
    public void test_EC11() throws MalformedURLException, InvalidSessionIDException {
        //产生一个不存在的session
        Integer wrongSession=null;
        while(wrongSession!=null && pb.getSessionIDs().containsKey(wrongSession)){
            wrongSession = new Random().nextInt(Integer.MAX_VALUE);
        }
        pb.updateDetails(wrongSession, new URL("http://www.google.com"),null,null);
    }

    @Test
    public void test_EC12() throws MalformedURLException, InvalidSessionIDException, NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException {
        Integer sessionOfMary = pb.loginUser("Mary","abcd1234ABCD");
        pb.updateDetails(sessionOfMary, new URL("http://www.google.com"),null,null);
    }

    @Test(expected = MalformedURLException.class)
    public void test_EC13() throws MalformedURLException, InvalidSessionIDException, NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException {
        Integer sessionOfMary = pb.loginUser("Mary","abcd1234ABCD");
        pb.updateDetails(sessionOfMary, new URL("ftp://www.google.com"),null,null);
    }

    /**
     * mutant-4
     */
    @Test(expected = NoSuchURLException.class)
    public void test_EC14() throws MalformedURLException, InvalidSessionIDException, NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException, NoSuchURLException {
        Integer sessionOfMary = pb.loginUser("Mary","abcd1234ABCD");
        pb.updateDetails(sessionOfMary, new URL("https://www.google.com"),"Maryy",null);

        pb.retrieveDetails (sessionOfMary, new URL("https://www.google.com"));
    }

    @Test
    public void test_EC15() throws MalformedURLException, InvalidSessionIDException, NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException {
        Integer sessionOfMary = pb.loginUser("Mary","abcd1234ABCD");
        pb.updateDetails(sessionOfMary, new URL("https://www.google.com"),"Maryy","123");
    }

    /**
     * Test updateDetail()
     */
    @Test(expected = InvalidSessionIDException.class)
    public void test_EC16() throws MalformedURLException, InvalidSessionIDException, NoSuchURLException {
        //产生一个不存在的session
        Integer wrongSession=null;
        while(wrongSession!=null && pb.getSessionIDs().containsKey(wrongSession)){
            wrongSession = new Random().nextInt(Integer.MAX_VALUE);
        }
        pb.retrieveDetails(wrongSession, new URL("https://www.google.com"));
    }

    @Test
    public void test_EC17() throws MalformedURLException, InvalidSessionIDException, NoSuchURLException, NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException {
        Integer sessionOfMary = pb.loginUser("Mary","abcd1234ABCD");
        pb.updateDetails(sessionOfMary, new URL("http://www.google.com"),"Maryy","123");
        pb.retrieveDetails(sessionOfMary, new URL("http://www.google.com"));
    }

    @Test(expected = MalformedURLException.class)
    public void test_EC18() throws MalformedURLException, InvalidSessionIDException, NoSuchURLException, NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException {
        Integer sessionOfMary = pb.loginUser("Mary","abcd1234ABCD");
        pb.retrieveDetails(sessionOfMary, new URL("ftp://www.google.com"));
    }

    @Test(expected = NoSuchURLException.class)
    public void test_EC19() throws MalformedURLException, InvalidSessionIDException, NoSuchURLException, NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException {
        Integer sessionOfMary = pb.loginUser("Mary","abcd1234ABCD");
        pb.retrieveDetails(sessionOfMary, new URL("https://www.youtube.com"));
    }

    @Test(expected = NoSuchURLException.class)
    public void test_EC20() throws MalformedURLException, InvalidSessionIDException, NoSuchURLException, NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException {
        Integer sessionOfMary = pb.loginUser("Mary","abcd1234ABCD");
        pb.updateDetails(sessionOfMary, new URL("http://www.google.com"),"Maryy","123");
        pb.retrieveDetails(sessionOfMary, new URL("https://www.youtube.com"));
    }

    @Test
    public void test_EC21() throws MalformedURLException, InvalidSessionIDException, NoSuchURLException, NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException {
        Integer sessionOfMary = pb.loginUser("Mary","abcd1234ABCD");
        pb.updateDetails(sessionOfMary, new URL("https://www.google.com"),"Maryy","123");
        pb.retrieveDetails(sessionOfMary, new URL("https://www.google.com"));
    }







    //This test should fail.
    //To provide additional feedback when a test fails, an error message
    //can be included
  /*  @Test public void aFailedTest()
    {
        //include a message for better feedback
        final int expected = 2;
        final int actual = 1 + 2;
        assertEquals("Some failure message", expected, actual);
    }*/

    //Any method annotated with "@After" will be executed after each test,
    //allowing the tester to release any shared resources used in the setup.
 /*   @After public void tearDown()
    {
    }
*/
    //Any method annotation with "@Test" is executed as a test.
 /*   @Test public void aTest()
    {
        //the assertEquals method used to check whether two values are
        //equal, using the equals method
        final int expected = 2;
        final int actual = 1 + 1;
        assertEquals(expected, actual);
    }
*/
   /* @Test public void anotherTest()
            throws DuplicateUserException, WeakPassphraseException
    {
        pb.addUser("passbookUsername", "properPassphrase1");

        //the assertTrue method is used to check whether something holds.
        assertTrue(pb.isUser("passbookUsername"));
        assertFalse(pb.isUser("nonUser"));
    }*/

    //To test an exception, specify the expected exception after the @Test
  /*  @Test(expected = java.io.IOException.class)
    public void anExceptionTest()
            throws Throwable
    {
        throw new java.io.IOException();
    }*/
}
