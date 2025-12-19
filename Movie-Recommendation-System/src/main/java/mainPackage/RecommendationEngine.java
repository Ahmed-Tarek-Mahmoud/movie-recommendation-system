package mainPackage;

import java.util.*;

/**
 * Recommendation Engine for mainPackage.Movie Recommendation System Recommends movies based
 * on genre preferences Dependencies: mainPackage.Movie and mainPackage.User classes
 */

public class RecommendationEngine {

	/**
	 * Generates movie recommendations for a user based on their liked movies
	 * 
	 * @param likedMovieIds List of movie IDs that the user likes
	 * @param allMovies     Map of all movies (movieId -> Movie object)
	 * @return List of recommended movie titles
	 */

	public List<String> generateRecommendations(List<String> likedMovieIds, Map<String, Movie> allMovies) {
		if (likedMovieIds == null || allMovies == null) {
			throw new IllegalArgumentException("Input parameters cannot be null");
		}

		if (likedMovieIds.isEmpty()) {
			return new ArrayList<>();
		}

		// Step 1: Extract genres from liked movies
		Set<String> likedGenres = extractLikedGenres(likedMovieIds, allMovies);

		// Step 2: Find all movies matching those genres
		Set<String> recommendedMovieTitles = findMoviesByGenres(likedGenres, allMovies);

		// Step 3: Remove already liked movies from recommendations
		removeAlreadyLikedMovies(recommendedMovieTitles, likedMovieIds, allMovies);

		// Step 4: Return as sorted list for consistent output
		List<String> recommendations = new ArrayList<>(recommendedMovieTitles);
		Collections.sort(recommendations);

		return recommendations;
	}

	/**
	 * Extracts all unique genres from the user's liked movies
	 * 
	 * @param likedMovieIds List of movie IDs the user likes
	 * @param allMovies     Map of all available movies
	 * @return Set of genre strings
	 */
	private Set<String> extractLikedGenres(List<String> likedMovieIds, Map<String, Movie> allMovies) {
		Set<String> genres = new HashSet<>();

		for (String movieId : likedMovieIds) {
			Movie movie = allMovies.get(movieId);
			if (movie != null && movie.getGenres() != null) {
				genres.addAll(movie.getGenres());
			}
		}

		return genres;
	}

	/**
	 * Finds all movies that match any of the specified genres
	 * 
	 * @param genres    Set of genres to match
	 * @param allMovies Map of all available movies
	 * @return Set of movie titles that match the genres
	 */
	private Set<String> findMoviesByGenres(Set<String> genres, Map<String, Movie> allMovies) {
		Set<String> matchingTitles = new HashSet<>();

		for (Movie movie : allMovies.values()) {
			if (movie != null && movie.getGenres() != null) {
				for (String genre : movie.getGenres()) {
					if (genres.contains(genre)) {
						matchingTitles.add(movie.getTitle());
						break; // Found a match, no need to check other genres
					}
				}
			}
		}

		return matchingTitles;
	}

	/**
	 * Removes movies that the user already likes from recommendations
	 * 
	 * @param recommendedTitles Set of recommended movie titles (modified in place)
	 * @param likedMovieIds     List of movie IDs the user already likes
	 * @param allMovies         Map of all available movies
	 */
	private void removeAlreadyLikedMovies(Set<String> recommendedTitles, List<String> likedMovieIds,
			Map<String, Movie> allMovies) {
		for (String likedId : likedMovieIds) {
			Movie likedMovie = allMovies.get(likedId);
			if (likedMovie != null) {
				recommendedTitles.remove(likedMovie.getTitle());
			}
		}
	}

	/**
	 * Generates recommendations for multiple users using a list of movies
	 * 
	 * @param users     List of mainPackage.User objects
	 * @param allMovies List of all available movies
	 * @return Map of userId -> List of recommended movie titles
	 */
	public Map<String, List<String>> generateRecommendationsForUsers(List<User> users, List<Movie> allMovies) {

		if (users == null || allMovies == null) {
			throw new IllegalArgumentException("Input parameters cannot be null");
		}

		Map<String, List<String>> recommendations = new HashMap<>();

		for (User user : users) {
			List<String> userRecommendations = generateRecommendations(user.getLikedMovieIds(), allMovies);
			recommendations.put(user.getUserId(), userRecommendations);
		}

		return recommendations;
	}

	/**
	 * Generates movie recommendations for a user based on their liked movies using a list
	 * 
	 * @param likedMovieIds List of movie IDs that the user likes
	 * @param allMovies     List of all movies
	 * @return List of recommended movie titles
	 */
	public List<String> generateRecommendations(List<String> likedMovieIds, List<Movie> allMovies) {
		if (likedMovieIds == null || allMovies == null) {
			throw new IllegalArgumentException("Input parameters cannot be null");
		}

		if (likedMovieIds.isEmpty()) {
			return new ArrayList<>();
		}

		// Step 1: Extract genres from liked movies
		Set<String> likedGenres = extractLikedGenres(likedMovieIds, allMovies);

		// Step 2: Find all movies matching those genres
		Set<String> recommendedMovieTitles = findMoviesByGenres(likedGenres, allMovies);

		// Step 3: Remove already liked movies from recommendations
		removeAlreadyLikedMovies(recommendedMovieTitles, likedMovieIds, allMovies);

		// Step 4: Return as sorted list for consistent output
		List<String> recommendations = new ArrayList<>(recommendedMovieTitles);
		Collections.sort(recommendations);

		return recommendations;
	}

	/**
	 * Extracts all unique genres from the user's liked movies using a list
	 * 
	 * @param likedMovieIds List of movie IDs the user likes
	 * @param allMovies     List of all available movies
	 * @return Set of genre strings
	 */
	private Set<String> extractLikedGenres(List<String> likedMovieIds, List<Movie> allMovies) {
		Set<String> genres = new HashSet<>();

		for (String movieId : likedMovieIds) {
			Movie movie = allMovies.stream()
					.filter(m -> m.getMovieId().equals(movieId))
					.findFirst()
					.orElse(null);
			if (movie != null && movie.getGenres() != null) {
				genres.addAll(movie.getGenres());
			}
		}

		return genres;
	}

	/**
	 * Finds all movies that match any of the specified genres using a list
	 * 
	 * @param genres    Set of genres to match
	 * @param allMovies List of all available movies
	 * @return Set of movie titles that match the genres
	 */
	private Set<String> findMoviesByGenres(Set<String> genres, List<Movie> allMovies) {
		Set<String> matchingTitles = new HashSet<>();

		for (Movie movie : allMovies) {
			if (movie != null && movie.getGenres() != null) {
				for (String genre : movie.getGenres()) {
					if (genres.contains(genre)) {
						matchingTitles.add(movie.getTitle());
						break; // Found a match, no need to check other genres
					}
				}
			}
		}

		return matchingTitles;
	}

	/**
	 * Removes movies that the user already likes from recommendations using a list
	 * 
	 * @param recommendedTitles Set of recommended movie titles (modified in place)
	 * @param likedMovieIds     List of movie IDs the user already likes
	 * @param allMovies         List of all available movies
	 */
	private void removeAlreadyLikedMovies(Set<String> recommendedTitles, List<String> likedMovieIds,
			List<Movie> allMovies) {
		for (String likedId : likedMovieIds) {
			Movie likedMovie = allMovies.stream()
					.filter(m -> m.getMovieId().equals(likedId))
					.findFirst()
					.orElse(null);
			if (likedMovie != null) {
				recommendedTitles.remove(likedMovie.getTitle());
			}
		}
	}
}
