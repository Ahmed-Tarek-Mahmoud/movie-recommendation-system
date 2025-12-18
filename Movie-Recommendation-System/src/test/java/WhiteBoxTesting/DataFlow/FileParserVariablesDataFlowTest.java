package WhiteBoxTesting.DataFlow;

import mainPackage.FileParser;
import mainPackage.Movie;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class FileParserVariablesDataFlowTest extends DataFlowTestBase {

    @Test
    public void testMovieName_SingleWord() throws IOException {
        writeToFile("Inception,I001\nAction,Sci-Fi\n");
        FileParser.loadMovies(tempFile.getAbsolutePath(), movies);
        Movie movie = movies.get("I001");
        assertNotNull(movie);
        assertEquals("Inception", movie.getTitle());
    }

    @Test
    public void testMovieName_MultiWord() throws IOException {
        writeToFile("The Dark Knight,TDK001\nAction,Drama\n");
        FileParser.loadMovies(tempFile.getAbsolutePath(), movies);
        Movie movie = movies.get("TDK001");
        assertEquals("The Dark Knight", movie.getTitle());
    }

    @Test
    public void testMovieName_Trimming() throws IOException {
        writeToFile("  Interstellar  ,I002\nSci-Fi\n");
        FileParser.loadMovies(tempFile.getAbsolutePath(), movies);
        Movie movie = movies.get("I002");
        assertEquals("Interstellar", movie.getTitle());
    }

    @Test
    public void testMovieId_FieldAndMapKey() throws IOException {
        writeToFile("Inception,I001\nAction\n");
        FileParser.loadMovies(tempFile.getAbsolutePath(), movies);
        Movie movie = movies.get("I001");
        assertNotNull(movie);
        assertEquals("I001", movie.getMovieId());
        assertTrue(movies.containsKey("I001"));
    }

    @Test
    public void testMovieId_Trimming() throws IOException {
        writeToFile("Inception,  I001  \nAction\n");
        FileParser.loadMovies(tempFile.getAbsolutePath(), movies);
        Movie movie = movies.get("I001");
        assertNotNull(movie);
        assertEquals("I001", movie.getMovieId());
    }

    @Test
    public void testGenres_SingleAndMultiple() throws IOException {
        writeToFile("Inception,I001\nAction,Sci-Fi,Thriller\n");
        FileParser.loadMovies(tempFile.getAbsolutePath(), movies);
        Movie movie = movies.get("I001");
        assertNotNull(movie.getGenres());
        assertEquals(3, movie.getGenres().size());
        assertTrue(movie.getGenres().contains("Action"));
        assertTrue(movie.getGenres().contains("Sci-Fi"));
    }

    @Test
    public void testGenres_Trimming() throws IOException {
        writeToFile("Inception,I001\n  Action  ,  Sci-Fi  \n");
        FileParser.loadMovies(tempFile.getAbsolutePath(), movies);
        Movie movie = movies.get("I001");
        assertEquals("Action", movie.getGenres().get(0));
        assertEquals("Sci-Fi", movie.getGenres().get(1));
    }
}
