package com.fei.myTest;

import com.fei.PassBook;
import com.fei.myException.*;
import org.junit.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import static org.junit.Assert.*;

//By extending PartitioningTests, we inherit tests from the script
public class BoundaryTests extends PartitioningTests
{
    @Test(expected = DuplicateUserException.class)
    public void test_EC1_1() throws WeakPassphraseException, DuplicateUserException {
        pb.addUser("Chris","Chris123");
        pb.addUser("Chris","Chris123");
    }

    @Test
    public void test_EC1_2() throws WeakPassphraseException, DuplicateUserException {
        pb.addUser("Chris","Chris123");
        pb.addUser("Mary","abcd1234ABCD");
    }

    @Test
    public void test_EC2_1() throws WeakPassphraseException, DuplicateUserException {
        pb.addUser("Mary","abcd12CD");
    }

    @Test(expected = WeakPassphraseException.class)
    public void test_EC2_2() throws WeakPassphraseException, DuplicateUserException {
        pb.addUser("Mary","ab12ABC");
    }



    /**
     * mutant-2
     */
    @Test(expected = WeakPassphraseException.class)
    public void test_EC3_1() throws WeakPassphraseException, DuplicateUserException {
        pb.addUser("Mary","{ABC1234");
    }

    /**
     * mutant-1
     */
    @Test
    public void test_EC3_2() throws WeakPassphraseException, DuplicateUserException {
        pb.addUser("Mary","abcABC999");
    }

    @Test(expected = WeakPassphraseException.class)
    public void test_EC4_1() throws WeakPassphraseException, DuplicateUserException {
        pb.addUser("Mary","abcd1234");
    }

    /**
     * mutant-3
     */
    @Test
    public void test_EC4_2() throws WeakPassphraseException, DuplicateUserException {
        pb.addUser("Mary","abcdABC1");
    }

    @Test(expected = WeakPassphraseException.class)
    public void test_EC5_1() throws WeakPassphraseException, DuplicateUserException {
        pb.addUser("Mary","abcdABCD");
    }

    /**
     * mutant-5
     */
    @Test
    public void test_EC5_2() throws WeakPassphraseException, DuplicateUserException {
        pb.addUser("Mary","abcAAA999");
    }

    @Test(expected = NoSuchUserException.class)
    public void test_EC7_1() throws NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException, WeakPassphraseException, DuplicateUserException {
        pb.addUser("Chris","Chris123");
        pb.loginUser("Chase","abcABC123");
    }

    @Test
    public void test_EC7_2() throws NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException, WeakPassphraseException, DuplicateUserException {
        pb.addUser("Chris","Chris123");
        pb.loginUser("Chris","Chris123");
    }

    @Test(expected = AlreadyLoggedInException.class)
    public void test_EC8_1() throws NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException, WeakPassphraseException, DuplicateUserException {
        pb.addUser("Chris","Chris123");
        pb.loginUser("Chris","Chris123");
        pb.loginUser("Chris","Chris123");
    }

    @Test
    public void test_EC8_2() throws NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException, WeakPassphraseException, DuplicateUserException {
        pb.addUser("Chris","Chris123");
        pb.addUser("Chace","Chace123");
        pb.loginUser("Chris","Chris123");
        pb.loginUser("Chace","Chace123");
    }

    @Test(expected = IncorrectPassphraseException.class)
    public void test_EC9_1() throws NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException, WeakPassphraseException, DuplicateUserException {
        pb.addUser("Chris","Chris123");
        pb.loginUser("Chris","Passw0rd");
    }

    @Test
    public void test_EC9_2() throws NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException, WeakPassphraseException, DuplicateUserException {
        pb.addUser("Chris","Chris123");
        pb.loginUser("Chris","Chris123");
    }

    @Test(expected = InvalidSessionIDException.class)
    public void test_EC11_1() throws MalformedURLException, InvalidSessionIDException {
        Integer wrongSession=null;
        wrongSession = new Random().nextInt(Integer.MAX_VALUE);
        pb.updateDetails(wrongSession,new URL("http://www.google.com"),"Chris","Chris123");
    }

    @Test
    public void test_EC11_2() throws MalformedURLException, InvalidSessionIDException, WeakPassphraseException, DuplicateUserException, NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException {
        pb.addUser("Chris","Chris123");
        int session = pb.loginUser("Chris", "Chris123");
        pb.updateDetails(session,new URL("http://www.google.com"),"Chris","Chris123");
    }


    @Test
    public void test_EC12_1() throws MalformedURLException, InvalidSessionIDException, WeakPassphraseException, DuplicateUserException, NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException {
        pb.addUser("Chris","Chris123");
        int session = pb.loginUser("Chris", "Chris123");
        pb.updateDetails(session,new URL("http://www.google.com"),"Chris","Chris123");
    }

    @Test(expected = MalformedURLException.class)
    public void test_EC12_2() throws MalformedURLException, InvalidSessionIDException, WeakPassphraseException, DuplicateUserException, NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException {
        pb.addUser("Chris","Chris123");
        int session = pb.loginUser("Chris", "Chris123");
        pb.updateDetails(session,new URL("ftp://www.google.com"),"Chris","Chris123");
    }

    @Test
    public void test_EC14_5() throws MalformedURLException, InvalidSessionIDException, WeakPassphraseException, DuplicateUserException, NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException {
        pb.addUser("Chris","Chris123");
        int session = pb.loginUser("Chris", "Chris123");
        pb.updateDetails(session,new URL("http://www.google.com"),"Chris","");
    }

    @Test
    public void test_EC14_6() throws MalformedURLException, InvalidSessionIDException, WeakPassphraseException, DuplicateUserException, NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException {
        pb.addUser("Chris","Chris123");
        int session = pb.loginUser("Chris", "Chris123");
        pb.updateDetails(session,new URL("http://www.google.com"),"Chris","Chris123");
    }

    @Test(expected = NoSuchURLException.class)
    public void test_EC19_5() throws MalformedURLException, InvalidSessionIDException, WeakPassphraseException, DuplicateUserException, NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException, NoSuchURLException {
        pb.addUser("Chris","Chris123");
        int session = pb.loginUser("Chris", "Chris123");
        pb.retrieveDetails(session,new URL("http://www.google.com"));
    }

    @Test
    public void test_EC19_6() throws MalformedURLException, InvalidSessionIDException, WeakPassphraseException, DuplicateUserException, NoSuchUserException, AlreadyLoggedInException, IncorrectPassphraseException, NoSuchURLException {
        pb.addUser("Chris","Chris123");
        int session = pb.loginUser("Chris", "Chris123");
        pb.updateDetails(session,new URL("http://www.google.com"),"Bob","Bob123");
        pb.retrieveDetails(session,new URL("http://www.google.com"));
    }

}
