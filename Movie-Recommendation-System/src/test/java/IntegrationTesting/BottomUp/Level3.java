package IntegrationTesting.BottomUp;

import mainPackage.Movie;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import mainPackage.*;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;


public class Level3 {

    @Test
    public void test_OutputGeneration() {
        String moviesPath = new File("src/test/resources/BottomUp/movies.txt").getAbsolutePath();
        String usersPath  = new File("src/test/resources/BottomUp/users.txt").getAbsolutePath();

        try {
            // Step 1: Parse files
            List<Movie> movieList = FileParser.loadMovies(moviesPath);
            List<User> userList = FileParser.loadUsers(usersPath);

            // Step 2: Validate parsed data
            ValidationService validationService =
                    new ValidationService(movieList, userList);

            assertDoesNotThrow(validationService::validateMovies);
            assertDoesNotThrow(validationService::validateUsers);

            // Step 3: Generate output file
            OutputGenerator outputGenerator = new OutputGenerator();
            assertDoesNotThrow(() -> outputGenerator.printRecommendation(userList, movieList));

            // Step 4: Verify output file exists
            File outputFile = new File("recommendations.txt");
            assertTrue(outputFile.exists(), "Output file should exist");

            // Step 5: Verify file content (basic check)
            List<String> lines = Files.readAllLines(outputFile.toPath());

            // Check first line (Michael)
            assertTrue(lines.get(0).contains("Michael"));
            assertTrue(lines.get(1).contains("Avengers: Endgame"));

            // Check Alice
            assertTrue(lines.get(2).contains("Alice"));
            assertTrue(lines.get(3).contains("The Matrix"));

        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void test_OutputGeneration_MichaelOnly() {
        String moviesPath = new File("src/test/resources/BottomUp/movies.txt").getAbsolutePath();
        String usersPath  = new File("src/test/resources/BottomUp/users.txt").getAbsolutePath();

        try {
            List<Movie> movieList = FileParser.loadMovies(moviesPath);
            List<User> userList = FileParser.loadUsers(usersPath);

            ValidationService validationService = new ValidationService(movieList, userList);
            assertDoesNotThrow(validationService::validateMovies);
            assertDoesNotThrow(validationService::validateUsers);

            // Keep only Michael
            userList = userList.stream()
                    .filter(u -> u.getUserId().equals("123456789"))
                    .toList();

            OutputGenerator outputGenerator = new OutputGenerator();
            final List<User> filteredUsers = userList.stream()
                    .filter(u -> u.getUserId().equals("123456789"))
                    .toList();

            assertDoesNotThrow(() -> outputGenerator.printRecommendation(filteredUsers, movieList));

            File outputFile = new File("recommendations.txt");
            assertTrue(outputFile.exists());

            List<String> lines = Files.readAllLines(outputFile.toPath());
            assertTrue(lines.get(0).contains("Michael"));
            assertTrue(lines.get(1).contains("Avengers: Endgame"));

        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void test_OutputGeneration_MichaelOnly_Passes() {
        String moviesPath = new File("src/test/resources/BottomUp/movies.txt").getAbsolutePath();
        String usersPath  = new File("src/test/resources/BottomUp/users.txt").getAbsolutePath();

        try {
            // Step 1: Parse files
            List<Movie> movieList = FileParser.loadMovies(moviesPath);
            List<User> userList = FileParser.loadUsers(usersPath);

            // Step 2: Validate
            ValidationService validationService = new ValidationService(movieList, userList);
            assertDoesNotThrow(validationService::validateMovies);
            assertDoesNotThrow(validationService::validateUsers);

            // Step 3: Keep only Michael
            final List<User> michaelList = userList.stream()
                    .filter(u -> u.getUserId().equals("123456789"))
                    .toList();

            // Step 4: Generate output
            OutputGenerator outputGenerator = new OutputGenerator();
            assertDoesNotThrow(() -> outputGenerator.printRecommendation(michaelList, movieList));

            // Step 5: Verify output
            File outputFile = new File("recommendations.txt");
            assertTrue(outputFile.exists(), "Output file should exist");

            List<String> lines = Files.readAllLines(outputFile.toPath());

            // Check Michael's recommendations
            int michaelIndex = -1;
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).contains("Michael")) {
                    michaelIndex = i;
                    break;
                }
            }
            assertTrue(michaelIndex != -1, "Michael should be in the output file");
            assertTrue(lines.get(michaelIndex + 1).contains("Avengers: Endgame"),
                    "Michael's recommendations should include Avengers: Endgame");

        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

}
