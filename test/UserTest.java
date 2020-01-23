import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;


public class UserTest {

    @Test
    public void test() {
        User student = new Student(2, "usman", "usmanadio@gmail.com", type.juniorStudent, new Date());
        User user = User.createUser("usmanadio@gmail.com", student);
        assertNull(user);
    }

    @Test
    public void testDelete() {
        User user = User.deleteUser("adio@gmail.com");
        assertNull(user);
    }

    @Test
    public void loginTest() {
        boolean res = User.loginUser("adio");
        assertFalse(res);
    }

    @Before
    public void createAccount() {
        User teacher = new Teacher(1, "usman", "adio@gmail.com", new Date());
        User.allUsers.put("adio@gmail.com", teacher);
    }

    @Test
    public void loginTestTrue() {
        boolean res = User.loginUser("adio@gmail.com");
        assertTrue(res);
    }

    @Test
    public void listOfUsersTest() {

    }
}