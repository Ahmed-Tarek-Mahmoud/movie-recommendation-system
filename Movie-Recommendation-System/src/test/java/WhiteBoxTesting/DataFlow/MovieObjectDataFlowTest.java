package WhiteBoxTesting.DataFlow;

import mainPackage.FileParser;
import mainPackage.Movie;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class MovieObjectDataFlowTest extends DataFlowTestBase {

    @Test
    public void testConstructorToGetters() throws IOException {
        writeToFile("The Matrix,TM001\nAction,Sci-Fi\n");
        List<Movie> movieList = FileParser.loadMovies(tempFile.getAbsolutePath());
        Movie movie = movieList.stream().filter(m -> m.getMovieId().equals("TM001")).findFirst().orElse(null);
        assertNotNull(movie);
        assertEquals("The Matrix", movie.getTitle());
        assertEquals("TM001", movie.getMovieId());
        assertEquals(2, movie.getGenres().size());
    }

    @Test
    public void testToStringUsesFields() throws IOException {
        writeToFile("Inception,I001\nAction\n");
        List<Movie> movieList = FileParser.loadMovies(tempFile.getAbsolutePath());
        Movie movie = movieList.stream().filter(m -> m.getMovieId().equals("I001")).findFirst().orElse(null);
        assertNotNull(movie);
        String movieString = movie.toString();
        assertTrue(movieString.contains("Inception"));
        assertTrue(movieString.contains("I001"));
        assertTrue(movieString.contains("Action"));
    }

    @Test
    public void testGenresOrderPreserved() throws IOException {
        writeToFile("Inception,I001\nAction,Thriller,Sci-Fi,Drama\n");
        List<Movie> movieList = FileParser.loadMovies(tempFile.getAbsolutePath());
        Movie movie = movieList.stream().filter(m -> m.getMovieId().equals("I001")).findFirst().orElse(null);
        assertNotNull(movie);
        List<String> genres = movie.getGenres();
        assertEquals("Action", genres.get(0));
        assertEquals("Thriller", genres.get(1));
        assertEquals("Sci-Fi", genres.get(2));
        assertEquals("Drama", genres.get(3));
    }
}