package WhiteBoxTesting.DataFlow.UserTest;

import mainPackage.AppExceptions;
import mainPackage.ErrorCode;
import mainPackage.User;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DataFlowUserNameValidation extends DataFlowUserValidation {

    @Test
    public void testUserIdINull(){

        User user = new User(null,"123456789",new ArrayList<>());
        users.add(user);
        try {
            validationService.validateUsers();
            fail("Null Pointer Exception not thrown for null value");
        } catch (NullPointerException e) {
            assertNull(user.getUserName());
        }
    }
    @Test
    public void testUserIdLength1() {

        User user = new User("","123456789",new ArrayList<>());
        users.add(user);
        try {
            validationService.validateUsers();
            fail("App Exception not thrown for wrong user id length");
        } catch (AppExceptions e) {
            assertEquals(ErrorCode.USER_NAME_ERROR, e.getErrorCode());
        }
    }

    @Test
    public void testUserIdInvalid1() {
        // invalid: user Id has letter at beginning
        User user = new User(" ahmed","123456789",new ArrayList<>());
        users.add(user);
        try {
            validationService.validateUsers();
            fail("App Exception not thrown for user id with letter at beginning");
        } catch (AppExceptions e) {
            assertEquals(ErrorCode.USER_NAME_ERROR, e.getErrorCode());
        }
    }

    @Test
    public void testUserIdInvalid2() {

        User user = new User("a#med","123456789",new ArrayList<>());
        users.add(user);
        try {
            validationService.validateUsers();
            fail("App Exception not thrown for user id with letter at middle");
        } catch (AppExceptions e) {
            assertEquals(ErrorCode.USER_NAME_ERROR, e.getErrorCode());
        }
    }

    @Test
    public void testUserIdInvalid3() {

        User user = new User("ahmed","123456789",new ArrayList<>());
        users.add(user);
        validationService.validateUsers();
        assertEquals("ahmed",user.getUserName());
    }

}
