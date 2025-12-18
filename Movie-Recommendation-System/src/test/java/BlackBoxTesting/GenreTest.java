package BlackBoxTesting;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import mainPackage.Movie;

public class GenreTest {

    @Test
    public void genre_action_shouldPass() {
        Movie movie = new Movie(
                "The Matrix",
                "TM001",
                List.of("Action")
        );

        assertEquals("Action", movie.getGenres().get(0));
        assertTrue(movie.getGenres().contains("Action"));
    }

    @Test
    public void genre_sciFi_withHyphen_shouldPass() {
        Movie movie = new Movie(
                "Interstellar",
                "I002",
                List.of("Sci-Fi")
        );

        assertEquals("Sci-Fi", movie.getGenres().get(0));
    }

    @Test
    public void genre_multipleGenres_shouldPass() {
        Movie movie = new Movie(
                "Inception",
                "I003",
                List.of("Action", "Thriller", "Sci-Fi")
        );

        assertEquals(3, movie.getGenres().size());
        assertTrue(movie.getGenres().contains("Action"));
        assertTrue(movie.getGenres().contains("Thriller"));
        assertTrue(movie.getGenres().contains("Sci-Fi"));
    }

    @Test
    public void genre_drama_shouldPass() {
        Movie movie = new Movie(
                "Joker",
                "J004",
                List.of("Drama")
        );

        assertEquals("Drama", movie.getGenres().get(0));
    }

    @Test
    public void genre_adventure_shouldPass() {
        Movie movie = new Movie(
                "Indiana Jones",
                "IJ005",
                List.of("Adventure")
        );

        assertEquals("Adventure", movie.getGenres().get(0));
    }

    @Test
    public void genre_emptyList_shouldFail() {
        Movie movie = new Movie(
                "The Matrix",
                "TM006",
                List.of()
        );

        // Prove that Movie accepts empty genre list → Actually is empty
        assertTrue("Genre list is empty", movie.getGenres().isEmpty());
    }

    @Test
    public void genre_lowercase_shouldFail() {
        Movie movie = new Movie(
                "The Matrix",
                "TM007",
                List.of("action")
        );

        // Prove that Movie accepts invalid genre → Actually stores "action"
        assertEquals("action", movie.getGenres().get(0));
    }

    @Test
    public void genre_allUppercase_shouldFail() {
        Movie movie = new Movie(
                "Inception",
                "I008",
                List.of("ACTION")
        );

        // Prove that Movie accepts invalid genre → Actually stores "ACTION"
        assertEquals("ACTION", movie.getGenres().get(0));
    }

    @Test
    public void genre_withNumbers_shouldFail() {
        Movie movie = new Movie(
                "Avatar",
                "A009",
                List.of("Action123")
        );

        // Prove that Movie accepts invalid genre → Actually stores "Action123"
        assertEquals("Action123", movie.getGenres().get(0));
    }

    @Test
    public void genre_withLeadingSpace_shouldFail() {
        Movie movie = new Movie(
                "Titanic",
                "T010",
                List.of(" Romance")
        );

        // Prove that Movie accepts invalid genre → Actually stores " Romance"
        assertEquals(" Romance", movie.getGenres().get(0));
    }

    @Test
    public void genre_duplicates_shouldFail() {
        Movie movie = new Movie(
                "The Matrix",
                "TM011",
                List.of("Action", "Action")
        );

        // Prove that Movie accepts duplicate genres → Actually has 2 items
        assertEquals(2, movie.getGenres().size());
        assertEquals(1, movie.getGenres().stream().distinct().count());
    }
}
