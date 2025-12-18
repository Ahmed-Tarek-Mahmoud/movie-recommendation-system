package mainPackage;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<User> userList = FileParser.loadUsers("src/test/resources/users.txt");
            List<Movie> movieList = FileParser.loadMovies("src/test/resources/movies.txt");

            ValidationService validationService = new ValidationService(movieList, userList);
            validationService.validateUsers();
            validationService.validateMovies();

            OutputGenerator outputGenerator = new OutputGenerator();
            outputGenerator.printRecommendation(userList, movieList);
        } catch (AppExceptions e) {
            System.out.println(e.getMessage() + ", Id: " + e.getErrorCode().getCode());
            System.out.println("ERROR: " + e.getMessage());
            System.exit(e.getErrorCode().getCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
