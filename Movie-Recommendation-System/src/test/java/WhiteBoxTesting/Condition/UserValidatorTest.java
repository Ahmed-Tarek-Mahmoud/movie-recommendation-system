package WhiteBoxTesting.Condition;

import UnitTesting.ValidationServiceTest;
import mainPackage.ErrorCode;
import mainPackage.User;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTest extends ValidationServiceTest {

    // Valid Path 
    // C1: False, C2: False 
    @Test
    public void testValidUser_AllDigits() {
        String userName = "Alice";
        String userId = "123456789";
        User u1 = addUser(userName, userId, null);
        assertDoesNotThrow(() -> validationService.ValidUser(u1));
    }

    // Valid Path 
    // C1: False, C2: False 
    @Test
    public void testValidUser_DigitAndChar() {
        String userName = "Alice";
        String userId = "12345678A";
        User u1 = addUser(userName, userId, null);
        assertDoesNotThrow(() -> validationService.ValidUser(u1));
    }

    // C1: True 
    @Test
    public void testUserInvalid_InnerConditionTrue() {
        String userName = "Alice";
        String userId = "1234567A9"; 
        User u1 = addUser(userName, userId, null);
        assertValidationException(() -> validationService.ValidUser(u1),
                ErrorCode.USER_ID_ERROR,
                "mainPackage.User id must contain only digits and might end with char");
    }

    // C2: True 
    @Test
    public void testUserInvalid_EndConditionTrue() {
        String userName = "Alice";
        String userId = "12345678-"; 
        User u1 = addUser(userName, userId, null);
        assertValidationException(() -> validationService.ValidUser(u1),
                ErrorCode.USER_ID_ERROR,
                "mainPackage.User id must contain only digits and might end with char");
    }
}