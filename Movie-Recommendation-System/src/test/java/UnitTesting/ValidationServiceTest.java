package UnitTesting;

import mainPackage.*;
import org.junit.Assert;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public abstract class ValidationServiceTest {
    protected ValidationService validationService;
    protected List<Movie> movies;
    protected List<User> users;

    @Before
    public void setUp() {
        movies = new ArrayList<Movie>();
        users = new ArrayList<User>();
        validationService = new ValidationService(movies, users);
    }

    protected Movie addMovie(String title, String id, List<String> genres) {
        Movie movie = new Movie(title, id, genres);
        movies.add(movie);
        return movie;
    }

    protected User addUser(String userName, String userId, List<String> likedMovieIds) {
        User user = new User(userName, userId, likedMovieIds);
        users.add(user);
        return user;
    }
    
    protected void assertValidationException(Runnable runnable, ErrorCode expectedErrorCode, String expectedMessage) {
        try {
            runnable.run();
            fail("Expected AppException was not thrown.");
        } catch (AppExceptions e) {
            Assert.assertEquals(expectedMessage, e.getMessage());
            Assert.assertEquals(expectedErrorCode, e.getErrorCode());
        }
    }
}
