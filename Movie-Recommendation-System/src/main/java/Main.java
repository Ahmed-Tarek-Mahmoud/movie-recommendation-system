import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        try {
            Map<String, User> users = new TreeMap<String, User>();
            FileParser.loadUsers("src/test/resources/users.txt" , users);

            Map<String, Movie> movies = new TreeMap<String, Movie>();
            FileParser.loadMovies("src/test/resources/movies.txt", movies);

            List<User> userList = new ArrayList<User>();
            List<Movie> movieList = new ArrayList<Movie>();

            userList.addAll(users.values());
            movieList.addAll(movies.values());

            ValidationService validationService = new ValidationService(movieList, userList);
            validationService.validateUsers();
            validationService.validateMovies();

            OutputGenerator outputGenerator = new OutputGenerator();
            outputGenerator.printRecommendation(userList, movies);
        } catch (AppExceptions e) {
            System.out.println(e.getMessage() + ", Id: " + e.getErrorCode().getCode());
            System.out.println("ERROR: " + e.getMessage());
            System.exit(e.getErrorCode().getCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
