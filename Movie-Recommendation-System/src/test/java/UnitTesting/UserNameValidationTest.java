package UnitTesting;

import mainPackage.*;
import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Unit tests for username validation through mainPackage.ValidationService
 * Rules:
 * - Username must contain only alphabetic characters and spaces
 * - Username must not start with a space
 */
public class UserNameValidationTest {

    private ValidationService validationService;
    private List<User> users;
    private List<Movie> movies;

    @Before
    public void setUp() {
        users = new ArrayList<>();
        movies = new ArrayList<>();
        validationService = new ValidationService(movies, users);
    }

    @Test
    public void testValidUserName_OnlyAlphabetic() {
        // Valid: only alphabetic characters
        User user = new User("hazem", "U001", new ArrayList<>());
        users.add(user);
        validationService.UserNameValid(user.getUserName());
        assertEquals("hazem", user.getUserName());
    }

    @Test
    public void testValidUserName_WithSpaces() {
        // Valid: alphabetic characters with spaces in between
        User user = new User("hazem ayman", "U002", new ArrayList<>());
        users.add(user);
        validationService.UserNameValid(user.getUserName());
        assertEquals("hazem ayman", user.getUserName());
    }

    @Test
    public void testValidUserName_MultipleSpaces() {
        // Valid: alphabetic characters with multiple spaces
        User user = new User("hazem ayman ali", "U003", new ArrayList<>());
        users.add(user);
        validationService.UserNameValid(user.getUserName());
        assertEquals("hazem ayman ali", user.getUserName());
    }

    @Test
    public void testValidUserName_SingleCharacter() {
        // Valid: single alphabetic character
        User user = new User("A", "U004", new ArrayList<>());
        users.add(user);
        validationService.UserNameValid(user.getUserName());
        assertEquals("A", user.getUserName());
    }

    @Test
    public void testValidUserName_LowerCase() {
        // Valid: lowercase alphabetic characters
        User user = new User("hazem ayman", "U005", new ArrayList<>());
        users.add(user);
        validationService.UserNameValid(user.getUserName());
        assertEquals("hazem ayman", user.getUserName());
    }

    @Test
    public void testValidUserName_MixedCase() {
        // Valid: mixed case alphabetic characters
        User user = new User("Hazem Ayman", "U006", new ArrayList<>());
        users.add(user);
        validationService.UserNameValid(user.getUserName());
        assertEquals("Hazem Ayman", user.getUserName());
    }

    @Test(expected = AppExceptions.class)
    public void testInvalidUserName_StartsWithSpace() {
        // Invalid: username starts with space
        User user = new User(" hazem ayman", "U007", new ArrayList<>());
        users.add(user);
        validationService.UserNameValid(user.getUserName());
    }

    @Test
    public void testInvalidUserName_StartsWithSpace_CheckErrorCode() {
        // Invalid: username starts with space - verify error code and message
        try {
            User user = new User(" hazem ayman", "U008", new ArrayList<>());
            users.add(user);
            validationService.UserNameValid(user.getUserName());
            fail("Expected AppExceptions to be thrown");
        } catch (AppExceptions e) {
            assertEquals(ErrorCode.USER_NAME_ERROR, e.getErrorCode());
            assertTrue(e.getMessage().contains("User name") ||
                      e.getMessage().contains("username") || 
                      e.getMessage().contains("space"));
        }
    }

    @Test(expected = AppExceptions.class)
    public void testInvalidUserName_ContainsNumbers() {
        // Invalid: contains numeric characters
        User user = new User("hazem123", "U009", new ArrayList<>());
        users.add(user);
        validationService.UserNameValid(user.getUserName());
    }

    @Test
    public void testInvalidUserName_ContainsNumbers_CheckErrorCode() {
        // Invalid: contains numbers - verify error code
        try {
            User user = new User("hazem123", "U010", new ArrayList<>());
            users.add(user);
            validationService.UserNameValid(user.getUserName());
            fail("Expected AppExceptions to be thrown");
        } catch (AppExceptions e) {
            assertEquals(ErrorCode.USER_NAME_ERROR, e.getErrorCode());
        }
    }

    @Test(expected = AppExceptions.class)
    public void testInvalidUserName_ContainsSpecialCharacters() {
        // Invalid: contains special characters
        User user = new User("hazem@ayman", "U011", new ArrayList<>());
        users.add(user);
        validationService.UserNameValid(user.getUserName());
    }

    @Test(expected = AppExceptions.class)
    public void testInvalidUserName_ContainsUnderscore() {
        // Invalid: contains underscore
        User user = new User("hazem_ayman", "U012", new ArrayList<>());
        users.add(user);
        validationService.UserNameValid(user.getUserName());
    }

    @Test(expected = AppExceptions.class)
    public void testInvalidUserName_ContainsDash() {
        // Invalid: contains dash/hyphen
        User user = new User("hazem-ayman", "U013", new ArrayList<>());
        users.add(user);
        validationService.UserNameValid(user.getUserName());
    }

    @Test(expected = AppExceptions.class)
    public void testInvalidUserName_ContainsPunctuation() {
        // Invalid: contains punctuation
        User user = new User("hazem.ayman", "U014", new ArrayList<>());
        users.add(user);
        validationService.UserNameValid(user.getUserName());
    }

    @Test(expected = AppExceptions.class)
    public void testInvalidUserName_OnlySpaces() {
        // Invalid: only spaces
        User user = new User("   ", "U015", new ArrayList<>());
        users.add(user);
        validationService.UserNameValid(user.getUserName());
    }

    @Test(expected = AppExceptions.class)
    public void testInvalidUserName_EmptyString() {
        // Invalid: empty string
        User user = new User("", "U016", new ArrayList<>());
        users.add(user);
        validationService.UserNameValid(user.getUserName());
    }

    @Test(expected = AppExceptions.class)
    public void testInvalidUserName_MultipleLeadingSpaces() {
        // Invalid: multiple leading spaces
        User user = new User("  hazem ayman", "U017", new ArrayList<>());
        users.add(user);
        validationService.UserNameValid(user.getUserName());
    }

    @Test(expected = AppExceptions.class)
    public void testInvalidUserName_MixedInvalidCharacters() {
        // Invalid: mix of numbers and special characters
        User user = new User("hazem123@ayman", "U018", new ArrayList<>());
        users.add(user);
        validationService.UserNameValid(user.getUserName());
    }

    @Test
    public void testValidUserName_TrailingSpace() {
        // Edge case: trailing space 
        try {
            User user = new User("hazem ayman ", "U019", new ArrayList<>());
            users.add(user);
            validationService.UserNameValid(user.getUserName());
            assertEquals("hazem ayman ", user.getUserName());
        } catch (AppExceptions e) {
            // If validation rejects trailing spaces, this is expected
            assertEquals(ErrorCode.USER_NAME_ERROR, e.getErrorCode());
        }
    }

    @Test(expected = NullPointerException.class)
    public void testInvalidUserName_Null() {
        // Invalid: null username
        User user = new User(null, "U020", new ArrayList<>());
        users.add(user);
        validationService.UserNameValid(user.getUserName());
    }
}