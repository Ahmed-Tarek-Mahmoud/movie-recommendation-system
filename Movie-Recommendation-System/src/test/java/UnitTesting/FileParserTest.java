package UnitTesting;

import mainPackage.FileParser;
import mainPackage.Movie;
import mainPackage.User;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileParserTest {

    @Test
    public void testLoadUsers() throws IOException {

        Map<String, User> users = new HashMap<>();

        String path = new File("src/test/resources/users.txt").getAbsolutePath();

        FileParser.loadUsers(path, users);

        assertEquals(2, users.size());

        User michael = users.get("10");
        assertNotNull(michael);
        Assert.assertEquals("Michael", michael.getUserName());
        Assert.assertEquals("10", michael.getUserId());
        Assert.assertEquals(4, michael.getLikedMovieIds().size());
        assertTrue(michael.getLikedMovieIds().contains("1"));
        assertTrue(michael.getLikedMovieIds().contains("20"));

        User alice = users.get("11");
        assertNotNull(alice);
        Assert.assertEquals("Alice", alice.getUserName());
        Assert.assertEquals("11", alice.getUserId());
        Assert.assertEquals(2, alice.getLikedMovieIds().size());
        assertTrue(alice.getLikedMovieIds().contains("3"));
        assertTrue(alice.getLikedMovieIds().contains("4"));
    }

    @Test
    public void testLoadMovies() throws IOException {

        Map<String, Movie> movies = new HashMap<>();
        String path = new File("src/test/resources/movies.txt").getAbsolutePath();

        FileParser.loadMovies(path, movies);
        assertFalse(movies.isEmpty());


        assertTrue(movies.containsKey("1"));
        assertEquals(5, movies.size());
        Movie matrix = movies.get("1");
        Assert.assertEquals("The Matrix", matrix.getTitle());
        List<String> expectedGenres = Arrays.asList("Action", "Sci-Fi");
        Assert.assertEquals(expectedGenres, matrix.getGenres());

    }
}
