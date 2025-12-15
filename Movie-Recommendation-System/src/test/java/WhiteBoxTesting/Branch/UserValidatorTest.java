package WhiteBoxTesting.Branch;

import UnitTesting.ValidationServiceTest;
import mainPackage.ErrorCode;
import mainPackage.User;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTest extends ValidationServiceTest {

    // Valid Path - Covers all 'False' branches
    @Test
    public void testValidUser() {
        // Covers: userName != null, !userName.isEmpty(), userName.charAt(0) != ' ', 
        //         userName has only letters/spaces, userId != null, userId.length() == 9, 
        //         userId is all digits or digits + one letter at end.
        String userName = "Alice Smith";
        String userId = "12345678A";
        User u1 = addUser(userName, userId, null);
        assertDoesNotThrow(() -> validationService.ValidUser(u1));
    }

    // Branch 1: userName == null
    @Test
    public void testUserInvalid_NameIsNull() {
        String userId = "123456789";
        User u1 = addUser(null, userId, null);
        assertThrows(NullPointerException.class, () -> validationService.ValidUser(u1));
    }

    // Branch 2: userName.isEmpty()
    @Test
    public void testUserInvalid_NameIsEmpty() {
        String userName = "";
        String userId = "123456789";
        User u1 = addUser(userName, userId, null);
        assertValidationException(() -> validationService.ValidUser(u1),
                ErrorCode.USER_NAME_ERROR,
                "User name cannot be empty");
    }

    // Branch 3: userName starts with a space
    @Test
    public void testUserInvalid_NameStartsWithSpace() {
        String userName = " Alice";
        String userId = "123456789";
        User u1 = addUser(userName, userId, null);
        assertValidationException(() -> validationService.ValidUser(u1),
                ErrorCode.USER_NAME_ERROR,
                "User name cannot start with a space");
    }

    // Branch 4: userName contains invalid characters
    @Test
    public void testUserInvalid_NameContainsNumber() {
        String userName = "Alice1";
        String userId = "123456789";
        User u1 = addUser(userName, userId, null);
        assertValidationException(() -> validationService.ValidUser(u1),
                ErrorCode.USER_NAME_ERROR,
                "User name must contain only alphabetic characters and spaces");
    }
    
    // Branch 5: userId == null
    @Test
    public void testUserInvalid_IdIsNull() {
        String userName = "Alice";
        User u1 = addUser(userName, null, null);
        assertThrows(NullPointerException.class, () -> validationService.ValidUser(u1));
    }

    // Branch 6: userId.length() != 9
    @Test
    public void testUserInvalid_IdWrongLength() {
        String userName = "Alice";
        String userId = "12345678"; // Length 8
        User u1 = addUser(userName, userId, null);
        assertValidationException(() -> validationService.ValidUser(u1),
                ErrorCode.USER_ID_LENGTH_ERROR,
                "mainPackage.User id length must equal 9");
    }

    // Branch 7: 
    @Test
    public void testUserInvalid_IdWrongFormatMiddle() {
        String userName = "Alice";
        String userId = "1234567A9"; // 'A' in the middle 
        User u1 = addUser(userName, userId, null);
        assertValidationException(() -> validationService.ValidUser(u1),
                ErrorCode.USER_ID_ERROR,
                "mainPackage.User id must contain only digits and might end with char");
    }
    
    // Branch 8: userId contains non-alphanumeric at end
    @Test
    public void testUserInvalid_IdWrongFormatEnd() {
        String userName = "Alice";
        String userId = "12345678-"; 
        User u1 = addUser(userName, userId, null);
        assertValidationException(() -> validationService.ValidUser(u1),
                ErrorCode.USER_ID_ERROR,
                "mainPackage.User id must contain only digits and might end with char");
    }
}