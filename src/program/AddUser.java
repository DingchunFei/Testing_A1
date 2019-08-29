package program;

import javafx.util.Pair;
import myException.DuplicateUserException;
import myException.WeakPassphraseException;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AddUser {
    /**
     * The minimum length of a passphrase
     */
    public final static int MINIMUM_PASSPHRASE_LENGTH = 8;

    /**
     * Valid URL protocols for the passbook
     */
    public final static String[] VALID_URL_PROTOCOLS = {"http", "https"};

    //The passphrases (master passwords) for all users
    private Map<String, String> passphrases;

    //The login details for all users and the URLs they have stored
    private Map<String, PasswordTable> details;

    //Mapping from session IDs to usernames
    private Map<Integer, String> userIDs;

    //Mapping from usernames to sessionIDs
    private Map<String, Integer> sessionIDs;

    //A simple label to improve code readability
    private class PasswordTable extends HashMap<URL, Pair<String, String>> {
    }

    /**
     * Constructs an empty passbook.
     */
    public AddUser() {
        passphrases = new HashMap<String, String>();
        details = new HashMap<String, PasswordTable>();
        userIDs = new HashMap<Integer, String>();
        sessionIDs = new HashMap<String, Integer>();
    }

    /**
     * Adds a new user to the passbook.
     *
     * @param passbookUsername the username for the user to be added
     * @param passphrase       the passphrase (master password) for the user
     * @throws DuplicateUserException  if the username is already in the passbook
     * @throws WeakPassphraseException if the password does not fit the passphrase
     *                                 rules (see class documentation)
     *                                 <p>
     *                                 Assumption: passbookUsername and passphrase are non-null
     */
    public void addUser(String passbookUsername, String passphrase)
            throws DuplicateUserException, WeakPassphraseException {
        //Check if this user exists
        if (passphrases.containsKey(passbookUsername)) {
            throw new DuplicateUserException(passbookUsername);
        }
        //check the passphrase strength
        else {
            if (passphrase.length() < MINIMUM_PASSPHRASE_LENGTH) {
                throw new WeakPassphraseException(passphrase);
            }

            boolean containsLowerCase = false;
            boolean containsUpperCase = false;
            boolean containsNumber = false;
            for (int i = 0; i < passphrase.length(); i++) {

                if ('a' <= passphrase.charAt(i) && passphrase.charAt(i) <= 'z') {
                    containsLowerCase = true;
                } else if ('A' <= passphrase.charAt(i) && passphrase.charAt(i) <= 'Z') {
                    containsUpperCase = true;
                } else if ('0' <= passphrase.charAt(i) && passphrase.charAt(i) <= '9') {
                    containsNumber = true;
                }
            }

            if (!containsLowerCase || !containsUpperCase || !containsNumber) {
                throw new WeakPassphraseException(passphrase);
            }
        }

        passphrases.put(passbookUsername, passphrase);
        PasswordTable pt = new PasswordTable();
        details.put(passbookUsername, pt);
    }
}
