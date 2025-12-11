package mainPackage;

import java.util.List;

/**
 * mainPackage.User class representing a user entity
 */

public class User {
	private String userName;
	private String userId;
	private List<String> likedMovieIds;

	public User(String userName, String userId, List<String> likedMovieIds) {
		this.userName = userName;
		this.userId = userId;
		this.likedMovieIds = likedMovieIds;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserId() {
		return userId;
	}

	public List<String> getLikedMovieIds() {
		return likedMovieIds;
	}

	@Override
	public String toString() {
		return "User{name='" + userName + "', id='" + userId + "', likedMovies=" + likedMovieIds + "}";
	}
}