package UnitTesting;

import Regression.Regression;
import mainPackage.FileParser;
import mainPackage.Movie;
import mainPackage.User;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileParserTest {

    @Test
    public void testLoadUsers() throws IOException {

        String path = new File("src/test/resources/users.txt").getAbsolutePath();

        List<User> users = FileParser.loadUsers(path);

        assertEquals(2, users.size());

        User michael = users.stream().filter(u -> u.getUserId().equals("123456789")).findFirst().orElse(null);
        assertNotNull(michael);
        assertEquals("Michael", michael.getUserName());
        assertEquals("123456789", michael.getUserId());
        assertEquals(1, michael.getLikedMovieIds().size());
        assertTrue(michael.getLikedMovieIds().contains("TM001"));

        User alice = users.stream().filter(u -> u.getUserId().equals("987654321")).findFirst().orElse(null);
        assertNotNull(alice);
        assertEquals("Alice", alice.getUserName());
        assertEquals("987654321", alice.getUserId());
        assertEquals(1, alice.getLikedMovieIds().size());
        assertTrue(alice.getLikedMovieIds().contains("AE004"));
    }

    @Test
    @Category(Regression.class)
    public void testLoadMovies() throws IOException {

        String path = new File("src/test/resources/movies.txt").getAbsolutePath();

        List<Movie> movies = FileParser.loadMovies(path);
        assertFalse(movies.isEmpty());

        assertEquals(5, movies.size());
        Movie matrix = movies.stream().filter(m -> m.getMovieId().equals("TM001")).findFirst().orElse(null);
        assertNotNull(matrix);
        assertEquals("The Matrix", matrix.getTitle());
        List<String> expectedGenres = Arrays.asList("Action", "Sci-Fi");
        assertEquals(expectedGenres, matrix.getGenres());

    }
}

