package UnitTesting;

import mainPackage.Movie;
import mainPackage.RecommendationEngine;
import mainPackage.User;
import org.junit.Test;
import org.junit.Before;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Unit Tests for mainPackage.RecommendationEngine
 */

public class RecommendationEngineTest {

	private RecommendationEngine engine;
	private List<Movie> movieDatabase;

	@Before
	public void setUp() {
		engine = new RecommendationEngine();
		movieDatabase = createTestMovieDatabase();
	}

	private List<Movie> createTestMovieDatabase() {
		List<Movie> movies = new ArrayList<>();

		movies.add(new Movie("The Dark Knight", "TDK123", Arrays.asList("action", "drama")));
		movies.add(new Movie("Inception", "I456", Arrays.asList("action", "sci-fi")));
		movies.add(new Movie("Matrix", "M789", Arrays.asList("action", "sci-fi")));
		movies.add(new Movie("Terrifier", "T234", Arrays.asList("horror")));
		movies.add(new Movie("Central Intelligence", "CI00", Arrays.asList("comedy")));
		movies.add(new Movie("Conjuring", "C567", Arrays.asList("horror")));
		movies.add(new Movie("Weapons", "w203", Arrays.asList("horror")));
		movies.add(new Movie("The Shawshank Redemption", "TSR890", Arrays.asList("drama")));
		movies.add(new Movie("The Hangover", "TH111", Arrays.asList("comedy")));

		return movies;
	}

	// Basic functionality tests

	@Test
	public void testSingleMovieLiked() {
		List<String> likedMovies = Arrays.asList("TH111");
		List<String> recommendations = engine.generateRecommendations(likedMovies, movieDatabase);

		assertTrue(recommendations.contains("Central Intelligence"));
		assertFalse(recommendations.contains("The Hangover"));
	}

	@Test
	public void testMultipleMoviesSameGenre() {
		List<String> likedMovies = Arrays.asList("T234", "C567");
		List<String> recommendations = engine.generateRecommendations(likedMovies, movieDatabase);

		assertTrue(recommendations.contains("Weapons"));
		assertFalse(recommendations.contains("Conjuring"));
	}

	@Test
	public void testMultipleGenresLiked() {
		List<String> likedMovies = Arrays.asList("TDK123", "T234");
		List<String> recommendations = engine.generateRecommendations(likedMovies, movieDatabase);

		assertTrue(recommendations.contains("Inception"));
		assertTrue(recommendations.contains("Matrix"));
		assertTrue(recommendations.contains("Conjuring"));
		assertTrue(recommendations.contains("Weapons"));
		assertTrue(recommendations.contains("The Shawshank Redemption"));
		assertFalse(recommendations.contains("The Dark Knight"));
		assertFalse(recommendations.contains("Terrifier"));
	}

	@Test
	public void testEmptyLikedMoviesList() {
		List<String> likedMovies = new ArrayList<>();
		List<String> recommendations = engine.generateRecommendations(likedMovies, movieDatabase);

		assertTrue(recommendations.isEmpty());
	}

	@Test
	public void testNonExistentMovieId() {
		List<String> likedMovies = Arrays.asList("FAKE999");
		List<String> recommendations = engine.generateRecommendations(likedMovies, movieDatabase);

		assertTrue(recommendations.isEmpty());
	}

	@Test
	public void testEmptyMovieDatabase() {
		List<Movie> emptyDb = new ArrayList<>();
		List<String> likedMovies = Arrays.asList("TDK123");
		List<String> recommendations = engine.generateRecommendations(likedMovies, emptyDb);

		assertTrue(recommendations.isEmpty());
	}

	@Test
	public void testMultiGenreMovie() {
		List<String> likedMovies = Arrays.asList("TDK123");
		List<String> recommendations = engine.generateRecommendations(likedMovies, movieDatabase);

		assertTrue(recommendations.contains("Inception"));
		assertTrue(recommendations.contains("The Shawshank Redemption"));
		assertTrue(recommendations.contains("Matrix"));
		assertFalse(recommendations.contains("The Dark Knight"));
		assertEquals(3, recommendations.size());
	}

	// Null handling tests

	@Test
	public void testNullLikedMoviesList() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			engine.generateRecommendations(null, movieDatabase);
		});
		assertEquals("Input parameters cannot be null", exception.getMessage());
	}

	@Test
	public void testNullMovieDatabase() {
		List<String> likedMovies = Arrays.asList("TDK123");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			engine.generateRecommendations(likedMovies, (Map<String, Movie>) null);
		});
		assertEquals("Input parameters cannot be null", exception.getMessage());
	}

	// Output format tests

	@Test
	public void testRecommendationsAreSorted() {
		List<String> likedMovies = Arrays.asList("TDK123");
		List<String> recommendations = engine.generateRecommendations(likedMovies, movieDatabase);

		List<String> sortedCopy = new ArrayList<>(recommendations);
		Collections.sort(sortedCopy);
		assertEquals(sortedCopy, recommendations);
	}

	// Batch processing tests

	@Test
	public void testMultipleUsersRecommendations() {
		List<User> users = Arrays.asList(new User("John Doe", "123456789", Arrays.asList("TDK123")),
				new User("Jane Smith", "987654321", Arrays.asList("T234")));

		Map<String, List<String>> allRecommendations = engine.generateRecommendationsForUsers(users, (List<Movie>) movieDatabase);

		assertEquals(2, allRecommendations.size());

		List<String> johnRecs = allRecommendations.get("123456789");
		assertTrue(johnRecs.contains("Inception"));

		List<String> janeRecs = allRecommendations.get("987654321");
		assertTrue(janeRecs.contains("Conjuring"));
	}

	@Test
	public void testEmptyUsersList() {
		List<User> users = new ArrayList<>();
		Map<String, List<String>> recommendations = engine.generateRecommendationsForUsers(users, (List<Movie>) movieDatabase);

		assertTrue(recommendations.isEmpty());
	}

	@Test
	public void testNullUsersListException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			engine.generateRecommendationsForUsers((List<User>) null, (List<Movie>) movieDatabase);
		});
		assertEquals("Input parameters cannot be null", exception.getMessage());
	}
}