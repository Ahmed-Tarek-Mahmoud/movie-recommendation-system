package WhiteBoxTesting.DataFlow;

import mainPackage.FileParser;
import mainPackage.Movie;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class FileParserVariablesDataFlowTest extends DataFlowTestBase {

    @Test
    public void testMovieName_SingleWord() throws IOException {
        writeToFile("Inception,I001\nAction,Sci-Fi\n");
        List<Movie> movieList = FileParser.loadMovies(tempFile.getAbsolutePath());
        Movie movie = movieList.stream().filter(m -> m.getMovieId().equals("I001")).findFirst().orElse(null);
        assertNotNull(movie);
        assertEquals("Inception", movie.getTitle());
    }

    @Test
    public void testMovieName_MultiWord() throws IOException {
        writeToFile("The Dark Knight,TDK001\nAction,Drama\n");
        List<Movie> movieList = FileParser.loadMovies(tempFile.getAbsolutePath());
        Movie movie = movieList.stream().filter(m -> m.getMovieId().equals("TDK001")).findFirst().orElse(null);
        assertNotNull(movie);
        assertEquals("The Dark Knight", movie.getTitle());
    }

    @Test
    public void testMovieName_Trimming() throws IOException {
        writeToFile("  Interstellar  ,I002\nSci-Fi\n");
        List<Movie> movieList = FileParser.loadMovies(tempFile.getAbsolutePath());
        Movie movie = movieList.stream().filter(m -> m.getMovieId().equals("I002")).findFirst().orElse(null);
        assertNotNull(movie);
        assertEquals("Interstellar", movie.getTitle());
    }

    @Test
    public void testMovieId_FieldAndMapKey() throws IOException {
        writeToFile("Inception,I001\nAction\n");
        List<Movie> movieList = FileParser.loadMovies(tempFile.getAbsolutePath());
        Map<String, Movie> movies = movieList.stream().collect(Collectors.toMap(Movie::getMovieId, m -> m));
        Movie movie = movies.get("I001");
        assertNotNull(movie);
        assertEquals("I001", movie.getMovieId());
        assertTrue(movies.containsKey("I001"));
    }

    @Test
    public void testMovieId_Trimming() throws IOException {
        writeToFile("Inception,  I001  \nAction\n");
        List<Movie> movieList = FileParser.loadMovies(tempFile.getAbsolutePath());
        Movie movie = movieList.stream().filter(m -> m.getMovieId().equals("I001")).findFirst().orElse(null);
        assertNotNull(movie);
        assertEquals("I001", movie.getMovieId());
    }

    @Test
    public void testGenres_SingleAndMultiple() throws IOException {
        writeToFile("Inception,I001\nAction,Sci-Fi,Thriller\n");
        List<Movie> movieList = FileParser.loadMovies(tempFile.getAbsolutePath());
        Movie movie = movieList.stream().filter(m -> m.getMovieId().equals("I001")).findFirst().orElse(null);
        assertNotNull(movie);
        assertNotNull(movie.getGenres());
        assertEquals(3, movie.getGenres().size());
        assertTrue(movie.getGenres().contains("Action"));
        assertTrue(movie.getGenres().contains("Sci-Fi"));
    }

    @Test
    public void testGenres_Trimming() throws IOException {
        writeToFile("Inception,I001\n  Action  ,  Sci-Fi  \n");
        List<Movie> movieList = FileParser.loadMovies(tempFile.getAbsolutePath());
        Movie movie = movieList.stream().filter(m -> m.getMovieId().equals("I001")).findFirst().orElse(null);
        assertNotNull(movie);
        assertEquals("Action", movie.getGenres().get(0));
        assertEquals("Sci-Fi", movie.getGenres().get(1));
    }
}