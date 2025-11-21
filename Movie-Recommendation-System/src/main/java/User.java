import java.util.List;

/**
 * User class representing a user entity
 */

class User {
	private String userName;
	private String userId;
	private List<String> likedMovieIds;

	public User(String userName, String userId, List<String> likedMovieIds) {
		validateUserName(userName);
		this.userName = userName;
		this.userId = userId;
		this.likedMovieIds = likedMovieIds;
	}

	/**
	 * Validates the username according to the following rules:
	 * - Must contain only alphabetic characters and spaces
	 * - Must not start with a space
	 * @param userName the username to validate
	 * @throws AppExceptions if validation fails
	 */
	private void validateUserName(String userName) {
		if (userName == null) {
			throw new NullPointerException("User name cannot be null");
		}
		
		if (userName.isEmpty()) {
			throw new AppExceptions("User name cannot be empty", ErrorCode.USER_NAME_ERROR);
		}
		
		if (userName.charAt(0) == ' ') {
			throw new AppExceptions("User name cannot start with a space", ErrorCode.USER_NAME_ERROR);
		}
		
		// Check if userName contains only alphabetic characters and spaces
		for (char c : userName.toCharArray()) {
			if (!Character.isLetter(c) && c != ' ') {
				throw new AppExceptions("User name must contain only alphabetic characters and spaces", ErrorCode.USER_NAME_ERROR);
			}
		}
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