package WhiteBoxTesting.DataFlow;

import mainPackage.FileParser;
import mainPackage.Movie;
import mainPackage.ValidationService;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MovieParsingIntegrationDataFlowTest extends DataFlowTestBase {

    @Test
    public void testMultipleMoviesIndependentPaths() throws IOException {
        writeToFile(
            "The Matrix,TM001\nAction,Sci-Fi\n" +
            "Inception,I002\nAction,Thriller,Sci-Fi\n" +
            "Titanic,T003\nRomance,Drama\n"
        );
        List<Movie> movieList = FileParser.loadMovies(tempFile.getAbsolutePath());
        assertEquals(3, movieList.size());
        assertEquals("The Matrix", movieList.get(0).getTitle());
        assertEquals("Inception", movieList.get(1).getTitle());
        assertEquals("Titanic", movieList.get(2).getTitle());
    }

    @Test
    public void testLoopIterationsAndBufferedReader() throws IOException {
        writeToFile(
            "Movie1,M001\nAction\n" +
            "Movie2,M002\nDrama\n" +
            "Movie3,M003\nComedy\n"
        );
        List<Movie> movieList = FileParser.loadMovies(tempFile.getAbsolutePath());
        assertEquals(3, movieList.size());
        // second load should not fail if resources were closed properly
        List<Movie> movieList2 = FileParser.loadMovies(tempFile.getAbsolutePath());
        assertEquals(3, movieList2.size());
    }

    @Test
    public void testEmptyFileProducesNoMovies() throws IOException {
        writeToFile("");
        List<Movie> movieList = FileParser.loadMovies(tempFile.getAbsolutePath());
        assertTrue(movieList.isEmpty());
    }

    @Test
    public void testSingleMovieMinimalPath() throws IOException {
        writeToFile("Solo,S001\nAction\n");
        List<Movie> movieList = FileParser.loadMovies(tempFile.getAbsolutePath());
        assertEquals(1, movieList.size());
        assertEquals("Solo", movieList.get(0).getTitle());
    }

    @Test
    public void testSpecialCharactersInName() throws IOException {
        writeToFile("Movie: The Sequel,MTS001\nAction\n");
        List<Movie> movieList = FileParser.loadMovies(tempFile.getAbsolutePath());
        assertEquals("Movie: The Sequel", movieList.get(0).getTitle());
    }

    @Test
    public void testIntegration_ValidationPassesForValidMovies() throws IOException {
        writeToFile("The Matrix,TM001\nAction,Sci-Fi\nInception,I002\nAction,Thriller\n");
        List<Movie> movieList = FileParser.loadMovies(tempFile.getAbsolutePath());
        ValidationService validator = new ValidationService(movieList, new ArrayList<>());
        assertDoesNotThrow(() -> validator.validateMovies(), "Validation should pass for valid movies");
    }
}