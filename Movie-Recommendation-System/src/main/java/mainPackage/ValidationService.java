package mainPackage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidationService implements UserValidator , MovieValidator{

    private List<Movie> movies;
    private List<User> users;
    ArrayList<String> MovieIds, UserIds;

    private Set<String> movieIds , userIds;

    public ValidationService(List<Movie> movies, List<User> users) {
        this.movies = movies;
        this.users = users;
        movieIds = new HashSet<>();
        userIds = new HashSet<>();
        MovieIds = new ArrayList<>();
        UserIds = new ArrayList<>();
    }

    public void validateMovies() {
        for (Movie movie : movies) {
            ValidMovie(movie);

            String idSuffix = movie.getMovieId().substring(movie.getMovieId().length() -  3);
            // uniqueness check BEFORE adding
            if (!movieIds.add(idSuffix)) {
                throw new AppExceptions(
                        "Movie ID numbers " + movie.getMovieId() + " aren't unique",
                        ErrorCode.MOVIE_ID_UNIQUE_ERROR
                );
            }
        }
//        ensureMovieIdUniqueness();
    }

    public void validateUsers() {
        for (User user : users) {
            ValidUser(user);

            String userId = user.getUserId().substring(user.getUserId().length() -  3);
            // uniqueness check BEFORE adding
            if (!userIds.add(userId)) {
                throw new AppExceptions(
                        "User ID numbers " + user.getUserId() + " aren't unique",
                        ErrorCode.USER_ID_UNIQUE_ERROR
                );
            }
        }
//        ensureUserIdUniqueness();
    }

    public void ensureMovieIdUniqueness() {
        for (int i = 0; i < MovieIds.size(); i++) {
            String idI = MovieIds.get(i);
            for (int j = i + 1; j < MovieIds.size(); j++) {
                String idJ = MovieIds.get(j);
                if (idI.equals(idJ)) {
                    throw new AppExceptions("Movie ID numbers " + movies.get(j).getMovieId() + " aren't unique" , ErrorCode.MOVIE_ID_UNIQUE_ERROR);
                }
            }
        }
    }

    public void ensureUserIdUniqueness() {
        for (int i = 0; i < UserIds.size(); i++) {
            String idI = UserIds.get(i);
            for (int j = i + 1; j < UserIds.size(); j++) {
                String idJ = UserIds.get(j);
                if (idI.equals(idJ)) {
                    throw new AppExceptions("User ID numbers " + users.get(j).getUserId() + " aren't unique" , ErrorCode.USER_ID_UNIQUE_ERROR);
                }
            }
        }
    }


}
