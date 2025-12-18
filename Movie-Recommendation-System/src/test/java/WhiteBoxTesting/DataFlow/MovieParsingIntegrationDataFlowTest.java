package WhiteBoxTesting.DataFlow;

import mainPackage.FileParser;
import mainPackage.Movie;
import mainPackage.ValidationService;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MovieParsingIntegrationDataFlowTest extends DataFlowTestBase {

    @Test
    public void testMultipleMoviesIndependentPaths() throws IOException {
        writeToFile(
            "The Matrix,TM001\nAction,Sci-Fi\n" +
            "Inception,I002\nAction,Thriller,Sci-Fi\n" +
            "Titanic,T003\nRomance,Drama\n"
        );
        FileParser.loadMovies(tempFile.getAbsolutePath(), movies);
        assertEquals(3, movies.size());
        assertEquals("The Matrix", movies.get("TM001").getTitle());
        assertEquals("Inception", movies.get("I002").getTitle());
        assertEquals("Titanic", movies.get("T003").getTitle());
    }

    @Test
    public void testLoopIterationsAndBufferedReader() throws IOException {
        writeToFile(
            "Movie1,M001\nAction\n" +
            "Movie2,M002\nDrama\n" +
            "Movie3,M003\nComedy\n"
        );
        FileParser.loadMovies(tempFile.getAbsolutePath(), movies);
        assertEquals(3, movies.size());
        movies.clear();
        // second load should not fail if resources were closed properly
        FileParser.loadMovies(tempFile.getAbsolutePath(), movies);
        assertEquals(3, movies.size());
    }

    @Test
    public void testEmptyFileProducesNoMovies() throws IOException {
        writeToFile("");
        FileParser.loadMovies(tempFile.getAbsolutePath(), movies);
        assertTrue(movies.isEmpty());
    }

    @Test
    public void testSingleMovieMinimalPath() throws IOException {
        writeToFile("Solo,S001\nAction\n");
        FileParser.loadMovies(tempFile.getAbsolutePath(), movies);
        assertEquals(1, movies.size());
        assertEquals("Solo", movies.get("S001").getTitle());
    }

    @Test
    public void testSpecialCharactersInName() throws IOException {
        writeToFile("Movie: The Sequel,MTS001\nAction\n");
        FileParser.loadMovies(tempFile.getAbsolutePath(), movies);
        assertEquals("Movie: The Sequel", movies.get("MTS001").getTitle());
    }

    @Test
    public void testIntegration_ValidationPassesForValidMovies() throws IOException {
        writeToFile("The Matrix,TM001\nAction,Sci-Fi\nInception,I002\nAction,Thriller\n");
        FileParser.loadMovies(tempFile.getAbsolutePath(), movies);
        ValidationService validator = new ValidationService(new ArrayList<>(movies.values()), new ArrayList<>());
        assertDoesNotThrow(() -> validator.validateMovies(), "Validation should pass for valid movies");
    }
}