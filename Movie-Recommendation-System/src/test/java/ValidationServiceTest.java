import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public abstract class ValidationServiceTest {
    protected ValidationService validationService;
    protected List<Movie> movies;
    protected List<User> users;


    protected void assertValidationException(Runnable runnable, ErrorCode expectedErrorCode, String expectedMessage) {
        try {
            runnable.run();
            fail("Expected AppException was not thrown.");
        } catch (AppExceptions e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(expectedErrorCode, e.getErrorCode());
        }
    }
}
