import myException.DuplicateUserException;
import myException.WeakPassphraseException;
import org.junit.Test;
import program.AddUser;

public class Main {

    public static void main(String[] args) {

    }

    @Test
    public void test(){
        AddUser addUser = new AddUser();
        try {
            addUser.addUser("fifi","Abc123123jsnd");
        } catch (DuplicateUserException e) {
            e.printStackTrace();
        } catch (WeakPassphraseException e) {
            e.printStackTrace();
        }
    }
}
