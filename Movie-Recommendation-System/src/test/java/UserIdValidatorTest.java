import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
public class UserIdValidatorTest extends ValidationServiceTest {
    @Test
    public void testUserIdValid1(){
        // valid: 9 digits Id
        User user = new User("ahmed","123456789",new ArrayList<>());
        users.add(user);
        validationService.UserIdValid(user.getUserId());
        validationService.ensureUserIdUniqueness();
        assertEquals("123456789",user.getUserId());
    }

    @Test
    public void testUserIdValid2(){
        // valid: Id length 9 with 8 digit one lowercase letter at end
        User user = new User("ahmed","12345678a",new ArrayList<>());
        users.add(user);
        validationService.UserIdValid(user.getUserId());
        validationService.ensureUserIdUniqueness();
        assertEquals("12345678a",user.getUserId());
    }

    @Test
    public void testUserIdValid3(){
        // valid: Id length 9 with 8 digit one uppercase letter at end
        User user = new User("ahmed","12345678A",new ArrayList<>());
        users.add(user);
        validationService.UserIdValid(user.getUserId());
        validationService.ensureUserIdUniqueness();
        assertEquals("12345678A",user.getUserId());
    }

    @Test
    public void testUserIdINull(){
        // invalid: null user Id
        User user = new User("ahmed",null,new ArrayList<>());
        users.add(user);
        try {
            validationService.UserIdValid(user.getUserId());
            validationService.ensureUserIdUniqueness();
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
            validationService.UserIdValid(user.getUserId());
            validationService.ensureUserIdUniqueness();
            fail("App Exception not thrown for wrong user id length");
        } catch (AppExceptions e) {
            assertEquals(ErrorCode.USER_ID_LENGTH_ERROR, e.getErrorCode());
        }
    }

    @Test
    public void testUserIdLength2() {
        // invalid: user Id length 1 only
        User user = new User("ahmed","1",new ArrayList<>());
        users.add(user);
        try {
            validationService.UserIdValid(user.getUserId());
            validationService.ensureUserIdUniqueness();
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
            validationService.UserIdValid(user.getUserId());
            validationService.ensureUserIdUniqueness();
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
            validationService.UserIdValid(user.getUserId());
            validationService.ensureUserIdUniqueness();
            fail("App Exception not thrown for user id with letter at middle");
        } catch (AppExceptions e) {
            assertEquals(ErrorCode.USER_ID_ERROR, e.getErrorCode());
        }
    }

    @Test
    public void testUserIdInvalid3() {
        // invalid: user Id has not digit norletter at end
        User user = new User("ahmed","12345678#",new ArrayList<>());
        users.add(user);
        try {
            validationService.UserIdValid(user.getUserId());
            validationService.ensureUserIdUniqueness();
            fail("App Exception not thrown for user id has not digit norletter at end");
        } catch (AppExceptions e) {
            assertEquals(ErrorCode.USER_ID_ERROR, e.getErrorCode());
        }
    }

    @Test
    public void testUserIdUnique() {
        // valid: user Ids are unique
        User user1 = new User("ahmed","123456789",new ArrayList<>());
        users.add(user1);
        User user2 = new User("ahmed","12345678A",new ArrayList<>());
        users.add(user2);
        validationService.validateUsers();
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