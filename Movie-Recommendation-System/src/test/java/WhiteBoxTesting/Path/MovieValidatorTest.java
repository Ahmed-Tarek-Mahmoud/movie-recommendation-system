package WhiteBoxTesting.Path;

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

    // Invalid Title
    @Test
    public void testMovieInvalid_Title(){
        String title = "interstellar";
        String ID = "I001";
        List<String> genres = new ArrayList<String>();
        genres.add("Sci-Fi");
        Movie m1 = addMovie(title , ID , genres);
        assertValidationException(()-> validationService.ValidMovie(m1),
                ErrorCode.MOVIE_TITLE_ERROR,
                "Movie Title "+ title + " is wrong");
    }

    // Invalid ID
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

    // Invalid Genres
    @Test
    public void testInvalidMovie_Genre() {
        String title = "Interstellar";
        String ID = "I001";
        List<String> genres = new ArrayList<String>();
        Movie m1 = addMovie(title , ID , genres);
        assertValidationException(()-> validationService.ValidMovie(m1) ,
                ErrorCode.MOVIE_GENRE_ERROR,
                "genre category is empty");
    }

    // Unique
    @Test
    public void testMovieID_Uniqueness_Pass(){
        String title1 = "Interstellar";
        String ID1 = "I001";
        List<String> genres1 = new ArrayList<String>();
        genres1.add("Sci-Fi");
        Movie m1 = addMovie(title1 , ID1 , genres1);


        String title2 = "Alaaeldin";
        String ID2 = "A002";
        List<String> genres2 = new ArrayList<String>();
        genres2.add("Cartoon");
        Movie m2 = addMovie(title2 , ID2 , genres2);
        assertDoesNotThrow(()-> validationService.validateMovies());
    }

    // Duplicate
    @Test
    public void testMovieID_Uniqueness_Fail(){
        String title1 = "Interstellar";
        String ID1 = "I001";
        List<String> genres1 = new ArrayList<String>();
        genres1.add("Sci-Fi");
        Movie m1 = addMovie(title1 , ID1 , genres1);


        String title2 = "Alaaeldin";
        String ID2 = "A001";
        List<String> genres2 = new ArrayList<String>();
        genres2.add("Cartoon");
        Movie m2 = addMovie(title2 , ID2 , genres2);
        assertValidationException(() -> validationService.validateMovies(),
                ErrorCode.MOVIE_ID_UNIQUE_ERROR,
                "Movie ID numbers " + ID2 + " aren't unique");
    }
}

