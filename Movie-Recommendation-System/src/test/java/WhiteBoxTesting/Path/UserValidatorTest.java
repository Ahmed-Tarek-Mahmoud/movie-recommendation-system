package WhiteBoxTesting.Path;

import UnitTesting.ValidationServiceTest;
import mainPackage.ErrorCode;
import mainPackage.User;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTest extends ValidationServiceTest {

    // Path 1: Valid User 
    @Test
    public void testValidUser() {
        String userName = "Michael";
        String userId = "99999999S";
        User u1 = addUser(userName, userId, null);
        assertDoesNotThrow(() -> validationService.ValidUser(u1));
    }

    // Path 2: Invalid User Name
    @Test
    public void testUserInvalid_NameContainsSymbol() {
        String userName = "Michael$";
        String userId = "99999999S"; 
        User u1 = addUser(userName, userId, null);
        assertValidationException(() -> validationService.ValidUser(u1),
                ErrorCode.USER_NAME_ERROR,
                "User name must contain only alphabetic characters and spaces");
    }

    // Path 3: Invalid User ID 
    @Test
    public void testUserInvalid_IdTooLong() {
        String userName = "Michael"; 
        String userId = "9999999999"; // Length> 9
        User u1 = addUser(userName, userId, null);
        assertValidationException(() -> validationService.ValidUser(u1),
                ErrorCode.USER_ID_LENGTH_ERROR,
                "mainPackage.User id length must equal 9");
    }

    // Path 4: All User IDs are unique 
    @Test
    public void testUserID_Uniqueness_Pass() {
        String userName1 = "Alice";
        String userId1 = "12345678A";
        User u1 = addUser(userName1, userId1, null);

        String userName2 = "Bob";
        String userId2 = "98765432B";
        User u2 = addUser(userName2, userId2, null);
        
        assertDoesNotThrow(()-> validationService.validateUsers());
    }

    // Path 5: Duplicate User IDs exist 
    @Test
    public void testUserID_Uniqueness_Fail() {
        String userName1 = "Alice";
        String userId1 = "12345678A";
        User u1 = addUser(userName1, userId1, null);

        String userName2 = "Bob";
        String userId2 = "12345678A"; 
        User u2 = addUser(userName2, userId2, null);
        
        assertValidationException(() -> validationService.validateUsers(),
                ErrorCode.USER_ID_UNIQUE_ERROR,
                "User ID numbers " + userId2 + " aren't unique");
    }
}