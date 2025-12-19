package WhiteBoxTesting.DataFlow.UserTest;

import mainPackage.AppExceptions;
import mainPackage.ErrorCode;
import mainPackage.User;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
public class DataFlowUserIdValidation extends DataFlowUserValidation {

    @Test
    public void testUserIdINull(){
        // invalid: null user Id
        User user = new User("ahmed",null,new ArrayList<>());
        users.add(user);
        try {
            validationService.validateUsers();
            fail("Null Pointer Exception not thrown for null value");
        } catch (NullPointerException e) {
            assertNull(user.getUserId());
        }
    }
    @Test
    public void testUserIdLength1() {
        // invalid: user Id length 8 only
        User user = new User("ahmed","12345678",new ArrayList<>());
        users.add(user);
        try {
            validationService.validateUsers();
            fail("App Exception not thrown for wrong user id length");
        } catch (AppExceptions e) {
            assertEquals(ErrorCode.USER_ID_LENGTH_ERROR, e.getErrorCode());
        }
    }

    @Test
    public void testUserIdInvalid1() {
        // invalid: user Id has letter at beginning
        User user = new User("ahmed","A12345678",new ArrayList<>());
        users.add(user);
        try {
            validationService.validateUsers();
            fail("App Exception not thrown for user id with letter at beginning");
        } catch (AppExceptions e) {
            assertEquals(ErrorCode.USER_ID_ERROR, e.getErrorCode());
        }
    }

    @Test
    public void testUserIdInvalid2() {
        // invalid: user Id has letter at middle
        User user = new User("ahmed","1234A5678",new ArrayList<>());
        users.add(user);
        try {
            validationService.validateUsers();
            fail("App Exception not thrown for user id with letter at middle");
        } catch (AppExceptions e) {
            assertEquals(ErrorCode.USER_ID_ERROR, e.getErrorCode());
        }
    }

    @Test
    public void testUserId3() {
        // valid: 9 digits Id
        User user = new User("ahmed","123456789",new ArrayList<>());
        users.add(user);
        assertDoesNotThrow(() -> validationService.validateUsers());
        assertEquals("123456789",user.getUserId());
    }
}