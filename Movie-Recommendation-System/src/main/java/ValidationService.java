import java.util.ArrayList;
import java.util.List;

public class ValidationService implements UserValidator , MovieValidator{

    private List<Movie> movies;
    private List<User> users;
    ArrayList<String> MovieIds , UserIds;

    public ValidationService(List<Movie> movies, List<User> users) {
        this.movies = movies;
        this.users = users;
        MovieIds = new ArrayList<>();
        UserIds = new ArrayList<>();
    }

    public void validateMovies() {
        for (Movie movie : movies) {
            ValidMovie(movie);
            MovieIds.add(movie.getMovieId().substring(movie.getMovieId().length() -  3));
        }
        ensureMovieIdUniqueness();
    }

    public void validateUsers() {
        for (User user : users) {
            ValidUser(user);
            UserIds.add(user.getUserId());
        }
        ensureUserIdUniqueness();
    }

    private void ensureMovieIdUniqueness() {
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

    private void ensureUserIdUniqueness() {
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
