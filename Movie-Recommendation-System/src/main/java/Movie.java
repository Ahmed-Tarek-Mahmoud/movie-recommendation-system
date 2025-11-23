import java.util.List;

/**
 * Movie class representing a movie entity
 */

class Movie {
	private String title;
	private String movieId;
	private List<String> genres;

	public Movie(String title, String movieId, List<String> genres) {
		this.title = title;
		this.movieId = movieId;
		this.genres = genres;
	}

	public String getTitle() {
		return title;
	}

	public String getMovieId() {
		return movieId;
	}

	public List<String> getGenres() {
		return genres;
	}

	@Override
	public String toString() {
		return "Movie{title='" + title + "', id='" + movieId + "', genres=" + genres + "}";
	}
}