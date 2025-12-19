package IntegrationTesting.LevelZero;

import mainPackage.FileParser;
import mainPackage.Movie;
import mainPackage.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FileParserTest {

    @Test
    public void testLoadUsers() throws IOException {
        //Create a temporary file with test user data

        File tempFile = File.createTempFile("usersTest", ".txt");
        tempFile.deleteOnExit();

        String content = "Adnan, 123456789\n" +
                "AE004,T003\n" +
                "Ahmed, 987654321\n" +
                "I002,T003\n";

        Files.writeString(tempFile.toPath(),content);
        Map<String, User> users = new HashMap<>();
        FileParser.loadUsers(tempFile.getAbsolutePath(), users);


        assertEquals(2, users.size());


        User adnan = users.get("123456789");
        assertNotNull(adnan);
        assertEquals("Adnan", adnan.getUserName());
        assertEquals(List.of("AE004","T003"), adnan.getLikedMovieIds());


        User ahmed = users.get("987654321");
        assertNotNull(ahmed);
        assertEquals("Ahmed", ahmed.getUserName());
        assertEquals(List.of("I002","T003"), ahmed.getLikedMovieIds());
    }

    @Test
    public void testLoadMovies() throws IOException {
        File tempFile = File.createTempFile("moviesTest", ".txt");
        tempFile.deleteOnExit();

        String content = """
                The Matrix,TM001
                Action,Sci-Fi
                Titanic,T003
                Romance,Drama
                """;

        Files.writeString(tempFile.toPath(), content);

        Map<String, Movie> movies = new HashMap<>();
        FileParser.loadMovies(tempFile.getAbsolutePath(), movies);

        assertEquals(2, movies.size());

        Movie matrix = movies.get("TM001");
        assertNotNull(matrix);
        assertEquals("The Matrix", matrix.getTitle());
        assertEquals(List.of("Action","Sci-Fi"), matrix.getGenres());

        Movie titanic = movies.get("T003");
        assertNotNull(titanic);
        assertEquals("Titanic", titanic.getTitle());
        assertEquals(List.of("Romance","Drama"), titanic.getGenres());
    }

}
