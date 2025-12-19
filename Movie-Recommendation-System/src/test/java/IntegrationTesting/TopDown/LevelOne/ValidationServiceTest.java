package IntegrationTesting.LevelOne;

import mainPackage.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ValidationServiceTest {

    private ValidationService validationService;
    private List<Movie> testMovies;
    private List<User> testUsers;

    @Before
    public void setupLists(){
        testMovies = new ArrayList<>();
        testUsers = new ArrayList<>();
    }


    @Test
    public void testValidateMovies(){
        validationService = spy(new ValidationService(testMovies,testUsers));
        doNothing().when(validationService).ensureMovieIdUniqueness();
        validationService.validateMovies();
        verify(validationService, times(1)).ensureMovieIdUniqueness();
    }

    @Test
    public void testValidateUsers(){
        validationService = spy(new ValidationService(testMovies,testUsers));
        doNothing().when(validationService).ensureUserIdUniqueness();
        validationService.validateUsers();
        verify(validationService, times(1)).ensureUserIdUniqueness();
    }


    @Test
    public void testEnsureUserIdUniquenessPass(){
        testUsers.add(new User("Adnan", "123456789", List.of("TM001","I002")));
        testUsers.add(new User("Ahmed", "987654321", List.of("AE004","I002")));
        validationService = new ValidationService(testMovies, testUsers);
        assertDoesNotThrow(() -> validationService.validateUsers());
    }

    @Test
    public void testEnsureUserIdUniquenessFail(){
        testUsers.add(new User("Adnan", "123456789", List.of("TM001","I002")));
        testUsers.add(new User("Ahmed", "123456789", List.of("AE004","I002")));
        validationService = new ValidationService(testMovies, testUsers);
        AppExceptions exception = assertThrows(AppExceptions.class, ()-> validationService.validateUsers());
        assertEquals(ErrorCode.USER_ID_UNIQUE_ERROR,exception.getErrorCode());
    }


    @Test
    public void testEnsureMovieIdUniquenessPass(){
        testMovies.add(new Movie("The Matrix","TM001",List.of("Action","Sci-Fi")));
        testMovies.add(new Movie("Inception","I002",List.of("Action","Thriller","Sci-Fi")));
        testMovies.add(new Movie("Avengers: Endgame","AE004",List.of("Action","Adventure","Sci-Fi")));
        validationService = new ValidationService(testMovies, testUsers);
        assertDoesNotThrow(() -> validationService.validateMovies());
    }

    @Test
    public void testEnsureMovieIdUniquenessFail(){
        testMovies.add(new Movie("The Matrix","TM001",List.of("Action","Sci-Fi")));
        testMovies.add(new Movie("Inception","TM001",List.of("Action","Thriller","Sci-Fi")));
        testMovies.add(new Movie("Avengers: Endgame","TM001",List.of("Action","Adventure","Sci-Fi")));
        validationService = new ValidationService(testMovies, testUsers);
        AppExceptions exception = assertThrows(AppExceptions.class, ()-> validationService.validateMovies());
        assertEquals(ErrorCode.MOVIE_ID_LETTERS_ERROR,exception.getErrorCode());
    }


}
