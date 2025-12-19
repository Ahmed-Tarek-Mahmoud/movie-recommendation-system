package WhiteBoxTesting.DataFlow.UserTest;

import mainPackage.AppExceptions;
import mainPackage.ErrorCode;
import mainPackage.User;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
public class DataFlowUserIdUniqueness extends DataFlowUserValidation {
    @Test
    public void testUserIdUnique1() {
        // valid: user Ids are unique

        validationService.validateUsers();
    }
    @Test
    public void testUserIdUnique2() {
        // valid: user Ids are unique
        User user1 = new User("ahmed","123456789",new ArrayList<>());
        users.add(user1);
        assertDoesNotThrow(() -> validationService.validateUsers());

    }
    @Test
    public void testUserIdUnique3() {
        // valid: user Ids are unique
        User user1 = new User("ahmed","123456789",new ArrayList<>());
        users.add(user1);
        User user2 = new User("ahmed","12345678A",new ArrayList<>());
        users.add(user2);
        assertDoesNotThrow(() -> validationService.validateUsers());
    }
    @Test
    public void testUserIdUnique4() {
        // valid: user Ids are unique
        User user1 = new User("ahmed","123456789",new ArrayList<>());
        users.add(user1);
        User user2 = new User("ahmed","12345678A",new ArrayList<>());
        users.add(user2);
        User user3 = new User("ahmed","987654321",new ArrayList<>());
        users.add(user3);
        assertDoesNotThrow(() -> validationService.validateUsers());
    }

    @Test
    public void testUserIdNotUnique() {
        // invalid: user Ids are the same
        User user1 = new User("ahmed","123456789",new ArrayList<>());
        users.add(user1);
        User user2 = new User("ahmed","123456789",new ArrayList<>());
        users.add(user2);
        try {
            validationService.validateUsers();
            fail("App Exception not thrown for user ids are the same");
        } catch (AppExceptions e) {
            assertEquals(ErrorCode.USER_ID_UNIQUE_ERROR, e.getErrorCode());
        }
    }

}
