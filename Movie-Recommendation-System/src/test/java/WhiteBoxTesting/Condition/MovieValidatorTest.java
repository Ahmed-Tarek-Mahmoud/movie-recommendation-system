package WhiteBoxTesting.Condition;

import UnitTesting.ValidationServiceTest;
import mainPackage.ErrorCode;
import mainPackage.Movie;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieValidatorTest extends ValidationServiceTest {

    // Valid Path
    // C1: False C2: False
    @Test
    public void testValidMovie() {
        String title = "Interstellar";
        String ID = "I001";
        List<String> genres = new ArrayList<String>();
        genres.add("Sci-Fi");
        Movie m1 = addMovie(title , ID , genres);
        assertDoesNotThrow(() -> validationService.ValidMovie(m1));
    }

    // C1: True C2: False
    @Test
    public void testMovieInvalid_WrongIDLetters(){
        String title = "Interstellar";
        String ID = "X001";
        List<String> genres = new ArrayList<String>();
        genres.add("Sci-Fi");
        Movie m1 = addMovie(title , ID , genres);
        assertValidationException(()-> validationService.ValidMovie(m1) ,
                ErrorCode.MOVIE_ID_LETTERS_ERROR,
                "Movie ID letters "+ ID +" are wrong");
    }

    // C1: False C2: True
    @Test
    public void testMovieInvalid_WrongIDLength(){
        String title = "Interstellar";
        String ID = "I01";
        List<String> genres = new ArrayList<String>();
        genres.add("Sci-Fi");
        Movie m1 = addMovie(title , ID , genres);
        assertValidationException(()-> validationService.ValidMovie(m1) ,
                ErrorCode.MOVIE_ID_LETTERS_ERROR,
                "Movie ID letters "+ ID +" are wrong");
    }

    // C1: True C2: True
    @Test
    public void testMovieInvalid_BothCases(){
        String title = "Interstellar";
        String ID = "X0001";
        List<String> genres = new ArrayList<String>();
        genres.add("Sci-Fi");
        Movie m1 = addMovie(title , ID , genres);
        assertValidationException(()-> validationService.ValidMovie(m1) ,
                ErrorCode.MOVIE_ID_LETTERS_ERROR,
                "Movie ID letters "+ ID +" are wrong");
    }


}

