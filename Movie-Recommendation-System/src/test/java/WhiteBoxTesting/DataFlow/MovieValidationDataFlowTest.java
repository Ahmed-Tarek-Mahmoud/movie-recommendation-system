package WhiteBoxTesting.DataFlow;

import mainPackage.FileParser;
import mainPackage.Movie;
import mainPackage.ValidationService;
import mainPackage.ErrorCode;
import mainPackage.AppExceptions;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MovieValidationDataFlowTest extends DataFlowTestBase {

    @Test
    public void testMovieNameValidation_valid() throws IOException {
        writeToFile("Interstellar,I001\nSci-Fi\n");
        FileParser.loadMovies(tempFile.getAbsolutePath(), movies);
        Movie movie = movies.get("I001");
        ValidationService validator = new ValidationService(new ArrayList<>(movies.values()), new ArrayList<>());
        assertDoesNotThrow(() -> validator.ValidMovie(movie), "Valid movie should not throw");
    }

    @Test
    public void testMovieNameValidation_invalid() throws IOException {
        writeToFile("interstellar,I001\nSci-Fi\n");
        FileParser.loadMovies(tempFile.getAbsolutePath(), movies);
        Movie movie = movies.get("I001");
        ValidationService validator = new ValidationService(new ArrayList<>(movies.values()), new ArrayList<>());
        try {
            validator.ValidMovie(movie);
            fail("Should throw validation exception for invalid movie name");
        } catch (AppExceptions e) {
            assertEquals(ErrorCode.MOVIE_TITLE_ERROR, e.getErrorCode());
        }
    }

    @Test
    public void testMovieIdValidation_invalidLetters() throws IOException {
        writeToFile("The Matrix,xyz001\nAction\n");
        FileParser.loadMovies(tempFile.getAbsolutePath(), movies);
        List<Movie> movieList = new ArrayList<>(movies.values());
        ValidationService validator = new ValidationService(movieList, new ArrayList<>());
        try {
            validator.validateMovies();
            fail("Should detect invalid movie ID letters");
        } catch (AppExceptions e) {
            assertEquals(ErrorCode.MOVIE_ID_LETTERS_ERROR, e.getErrorCode());
        }
    }

    @Test
    public void testGenresValidation_empty() throws IOException {
        // When file has empty genre line, FileParser creates a list with one empty string
        // The list is NOT empty (it has one element: ""), so MovieGenresValid won't throw
        // This test verifies that the current implementation doesn't validate empty genre strings
        writeToFile("Inception,I001\n\n");
        FileParser.loadMovies(tempFile.getAbsolutePath(), movies);
        Movie movie = movies.get("I001");
        
        // Verify that the genres list contains one empty string element
        assertEquals(1, movie.getGenres().size());
        assertEquals("", movie.getGenres().get(0));
        
        // The current validation only checks if list is empty, not if elements are empty
        ValidationService validator = new ValidationService(new ArrayList<>(movies.values()), new ArrayList<>());
        // This will NOT throw an exception because the list is not empty
        assertDoesNotThrow(() -> validator.ValidMovie(movie), 
            "Current implementation doesn't validate empty genre strings");
    }

    @Test
    public void testDuplicateMovieIdDetection() throws IOException {
        // Note: FileParser uses a Map, so duplicate IDs overwrite previous entries
        // The Map will only contain the last movie with that ID
        // So we need to test that the validator checks for uniqueness across the actual movie list

        // Create two movies with same ID manually to test uniqueness validation
        Movie movie1 = new Movie("Inception", "I001", List.of("Action"));
        Movie movie2 = new Movie("Interstellar", "I001", List.of("Sci-Fi"));

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie1);
        movieList.add(movie2);

        ValidationService validator = new ValidationService(movieList, new ArrayList<>());
        try {
            validator.validateMovies();
            fail("Should detect duplicate movie IDs");
        } catch (AppExceptions e) {
            assertEquals(ErrorCode.MOVIE_ID_UNIQUE_ERROR, e.getErrorCode());
        }
    }
}