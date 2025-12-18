package IntegrationTesting.BottomUp;

import mainPackage.*;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;



public class Level2 {

    @Test
    public void test_ParseValidateAndRecommendMovies() {
        String moviesPath = new File("src/test/resources/BottomUp/movies.txt").getAbsolutePath();
        String usersPath  = new File("src/test/resources/BottomUp/users.txt").getAbsolutePath();

        try {
            // Step 1: Parse
            List<Movie> movieList = FileParser.loadMovies(moviesPath);
            List<User> userList = FileParser.loadUsers(usersPath);

            // Step 2: Validate (already tested in Level 1, but integrated here)
            ValidationService validationService = new ValidationService(movieList, userList);

            assertDoesNotThrow(validationService::validateMovies);
            assertDoesNotThrow(validationService::validateUsers);

            // Step 3: Recommendation Engine (NEW in Level 2)
            RecommendationEngine recommendationEngine = new RecommendationEngine();

            User user = userList.get(0);

            List<String> recommendations = recommendationEngine.generateRecommendations(
                            user.getLikedMovieIds(),
                            movieList
                    );

            // Step 4: Assert recommendation result
            assertNotNull(recommendations);
            assertFalse(recommendations.isEmpty());

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void test_ParseValidateAndRecommend_WithSharedGenres() {
        String moviesPath =
                new File("src/test/resources/BottomUp/movies.txt").getAbsolutePath();
        String usersPath =
                new File("src/test/resources/BottomUp/users.txt").getAbsolutePath();

        try {
            // Step 1: Parse files
            List<Movie> movieList = FileParser.loadMovies(moviesPath);
            List<User> userList = FileParser.loadUsers(usersPath);

            // Step 2: Validate parsed data
            ValidationService validationService = new ValidationService(movieList, userList);

            assertDoesNotThrow(validationService::validateMovies);
            assertDoesNotThrow(validationService::validateUsers);

            // Step 3: Recommendation Engine
            RecommendationEngine recommendationEngine = new RecommendationEngine();

            // Michael likes TM001 (Action, Sci-Fi)
            User michael = userList.stream()
                    .filter(u -> u.getUserId().equals("123456789"))
                    .findFirst()
                    .orElseThrow();

            List<String> recommendations =
                    recommendationEngine.generateRecommendations(
                            michael.getLikedMovieIds(),
                            movieList
                    );

            // Step 4: Assert recommendations
            assertNotNull(recommendations);
            assertEquals(List.of("Avengers: Endgame"), recommendations);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void test_ParseValidateAndRecommend_MultipleLikedMovies() {
        String moviesPath =
                new File("src/test/resources/BottomUp/movies.txt").getAbsolutePath();
        String usersPath  =
                new File("src/test/resources/BottomUp/users.txt").getAbsolutePath();

        try {
            // Step 1: Parse files
            List<Movie> movieList = FileParser.loadMovies(moviesPath);
            List<User> userList = FileParser.loadUsers(usersPath);

            // Step 2: Validate parsed data
            ValidationService validationService =
                    new ValidationService(movieList, userList);

            assertDoesNotThrow(validationService::validateMovies);
            assertDoesNotThrow(validationService::validateUsers);

            // Step 3: Recommendation Engine
            RecommendationEngine recommendationEngine =
                    new RecommendationEngine();

            // Alice likes AE004, HP003 (only AE004 exists in movies)
            User alice = userList.stream()
                    .filter(u -> u.getUserId().equals("987654321"))
                    .findFirst()
                    .orElseThrow();

            List<String> recommendations =
                    recommendationEngine.generateRecommendations(
                            alice.getLikedMovieIds(),
                            movieList
                    );

            // Step 4: Assert recommendations
            assertNotNull(recommendations);
            assertEquals(List.of("The Matrix"), recommendations);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}