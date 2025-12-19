package IntegrationTesting.BottomUp;

import Regression.Regression;
import mainPackage.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.experimental.categories.Category;


public class Level1 {

    private void assertValidationException(Runnable runnable, ErrorCode expectedErrorCode, String expectedMessage) {
        try {
            runnable.run();
            fail("Expected AppException was not thrown.");
        } catch (AppExceptions e) {
            Assert.assertEquals(expectedMessage, e.getMessage());
            Assert.assertEquals(expectedErrorCode, e.getErrorCode());
        }
    }

    @Test
    public void test_ParseAndValidateMovies(){
        String path = new File("src/test/resources/BottomUp/movies.txt").getAbsolutePath();

        try{
            List<Movie> movieList = FileParser.loadMovies(path);

            ValidationService validationService = new ValidationService(movieList,new ArrayList<User>());
            assertDoesNotThrow(validationService::validateMovies);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test_ParseAndValidateUser(){
        String path = new File("src/test/resources/BottomUp/users.txt").getAbsolutePath();

        try{
            List<User> userList = FileParser.loadUsers(path);

            ValidationService validationService = new ValidationService(new ArrayList<>(),userList);
            assertDoesNotThrow(()->validationService.validateUsers());
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    @Category(Regression.class)
    public void test_Movie_errorOrder(){
        String path = new File("src/test/resources/BottomUp/movies_error_order.txt").getAbsolutePath();

        try{
            List<Movie> movieList = FileParser.loadMovies(path);

            ValidationService validationService = new ValidationService(movieList,new ArrayList<User>());
            assertValidationException(() -> validationService.validateMovies(),
                    ErrorCode.MOVIE_ID_UNIQUE_ERROR,
                    "Movie ID numbers AE001 aren't unique");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    @Category(Regression.class)
    public void test_User_errorOrder(){
        String path = new File("src/test/resources/BottomUp/users_error_order.txt").getAbsolutePath();

        try {
            List<User> userList = FileParser.loadUsers(path);

            ValidationService validationService = new ValidationService(new ArrayList<>(),userList);
            assertValidationException(()->validationService.validateUsers(),
                    ErrorCode.USER_ID_UNIQUE_ERROR,
                    "User ID numbers 123456789 aren't unique");
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

}

