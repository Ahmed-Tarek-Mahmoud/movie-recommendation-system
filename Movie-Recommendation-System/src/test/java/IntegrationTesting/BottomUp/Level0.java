package IntegrationTesting.BottomUp;

import Regression.Regression;
import mainPackage.FileParser;
import mainPackage.Movie;
import mainPackage.User;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.experimental.categories.Category;


/**
 * This level is responsible to test the bottom most level of our program
 * Which will be covering the fileParser class which is considered the entry point
 * of our application ( 0 dependencies )
 * */
public class Level0 {
    @Test
    public void testLoadUsers() {
        String path = new File("src/test/resources/BottomUp/users.txt").getAbsolutePath();
        try{
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
            assertEquals(2, alice.getLikedMovieIds().size());
            assertTrue(alice.getLikedMovieIds().contains("HP003"));
            assertTrue(alice.getLikedMovieIds().contains("AE004"));
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    @Test
    @Category(Regression.class)
    public void testLoadUsers_EmptyLines() {
        String path = new File("src/test/resources/BottomUp/user_empty_lines.txt").getAbsolutePath();
        try{
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
            assertEquals(2, alice.getLikedMovieIds().size());
            assertTrue(alice.getLikedMovieIds().contains("HP003"));
            assertTrue(alice.getLikedMovieIds().contains("AE004"));
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }


    @Test
    public void testLoadMovies() {
        String path = new File("src/test/resources/BottomUp/movies.txt").getAbsolutePath();

        try{
            List<Movie> movies = FileParser.loadMovies(path);
            assertFalse(movies.isEmpty());

            assertEquals(2, movies.size());

            Movie matrix = movies.stream().filter(m -> m.getMovieId().equals("TM001")).findFirst().orElse(null);
            assertNotNull(matrix);
            assertEquals("The Matrix", matrix.getTitle());
            List<String> expectedGenres = Arrays.asList("Action", "Sci-Fi");
            assertEquals(expectedGenres, matrix.getGenres());

            Movie Avengers = movies.stream().filter(m -> m.getMovieId().equals("AE004")).findFirst().orElse(null);
            assertNotNull(Avengers);
            assertEquals("Avengers: Endgame", Avengers.getTitle());
            expectedGenres = Arrays.asList("Action", "Adventure" ,"Sci-Fi");
            assertEquals(expectedGenres, Avengers.getGenres());

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

}

