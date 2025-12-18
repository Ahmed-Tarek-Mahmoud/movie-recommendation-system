package BlackBoxTesting;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import mainPackage.Movie;

public class MovieTest {

    @Test
    public void validMovie_withMultipleGenres_shouldPass() {
        Movie movie = new Movie(
                "The Matrix",
                "TM001",
                List.of("Action", "Sci-Fi")
        );

        assertEquals("The Matrix", movie.getTitle());
        assertEquals("TM001", movie.getMovieId());
        assertEquals(List.of("Action", "Sci-Fi"), movie.getGenres());
    }

    @Test
    public void validMovie_withSingleGenre_shouldPass() {
        Movie movie = new Movie(
                "Titanic",
                "T002",
                List.of("Romance")
        );

        assertEquals("Titanic", movie.getTitle());
        assertEquals("T002", movie.getMovieId());
        assertEquals(1, movie.getGenres().size());
    }

    @Test
    public void validMovieId_withTwoCapitalLetters_shouldPass() {
        Movie movie = new Movie(
                "Avengers Endgame",
                "AE004",
                List.of("Action", "Adventure")
        );

        assertEquals("AE004", movie.getMovieId());
    }

    @Test
    public void validMovieId_withThreeCapitalLetters_shouldPass() {
        Movie movie = new Movie(
                "The Dark Knight",
                "TDK005",
                List.of("Action", "Drama")
        );

        assertEquals("TDK005", movie.getMovieId());
    }

    @Test
    public void validMovieTitle_singleWord_shouldPass() {
        Movie movie = new Movie(
                "Joker",
                "J007",
                List.of("Drama")
        );

        assertEquals("Joker", movie.getTitle());
    }

    @Test
    public void movieTitle_startingWithLowercase_shouldFail() {
        Movie movie = new Movie(
                "the Matrix",
                "TM001",
                List.of("Action")
        );

        // Prove that Movie accepts invalid title → Actually stores "the Matrix"
        assertEquals("the Matrix", movie.getTitle());
    }

    @Test
    public void movieTitle_withLowercaseWordInMiddle_shouldFail() {
        Movie movie = new Movie(
                "The dark Knight",
                "TDK002",
                List.of("Action")
        );

        // Prove that Movie accepts invalid title → Actually stores "The dark Knight"
        assertEquals("The dark Knight", movie.getTitle());
    }

    @Test
    public void movieId_notMatchingTitleInitials_shouldFail() {
        Movie movie = new Movie(
                "The Matrix",
                "XY001",
                List.of("Action")
        );

        // Prove that Movie accepts invalid ID → Actually stores "XY001"
        assertEquals("XY001", movie.getMovieId());
    }

    @Test
    public void movieId_withWrongNumberOfDigits_shouldFail() {
        Movie movie = new Movie(
                "Inception",
                "I01",
                List.of("Thriller")
        );

        // Prove that Movie accepts invalid ID → Actually stores "I01"
        assertEquals("I01", movie.getMovieId());
    }

    @Test
    public void movieId_withLowercaseLetters_shouldFail() {
        Movie movie = new Movie(
                "The Matrix",
                "tm001",
                List.of("Action")
        );

        // Prove that Movie accepts invalid ID → Actually stores "tm001"
        assertEquals("tm001", movie.getMovieId());
    }

    @Test
    public void movieId_missingOneInitial_shouldFail() {
        Movie movie = new Movie(
                "The Matrix",
                "T001",
                List.of("Action")
        );

        // Prove that Movie accepts invalid ID → Actually stores "T001"
        assertEquals("T001", movie.getMovieId());
    }

    @Test
    public void genre_emptyList_shouldFail() {
        Movie movie = new Movie(
                "The Matrix",
                "TM001",
                List.of()
        );

        // Prove that Movie accepts empty genre list → Actually is empty
        assertTrue(movie.getGenres().isEmpty());
    }
}
