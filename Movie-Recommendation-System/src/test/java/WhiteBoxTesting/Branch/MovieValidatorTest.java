package WhiteBoxTesting.Branch;

import UnitTesting.ValidationServiceTest;
import mainPackage.ErrorCode;
import mainPackage.Movie;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieValidatorTest extends ValidationServiceTest {

    // Valid Path
    @Test
    public void testValidMovie() {
        String title = "Interstellar";
        String ID = "I001";
        List<String> genres = new ArrayList<String>();
        genres.add("Sci-Fi");
        Movie m1 = addMovie(title , ID , genres);
        assertDoesNotThrow(() -> validationService.ValidMovie(m1));
    }

    @Test
    public void testMovieInvalid(){
        String title = "interstellar";
        String ID = "I001";
        List<String> genres = new ArrayList<String>();
        genres.add("Sci-Fi");
        Movie m1 = addMovie(title , ID , genres);
        assertValidationException(()-> validationService.ValidMovie(m1),
                ErrorCode.MOVIE_TITLE_ERROR,
                "Movie Title "+ title + " is wrong");
    }

}
