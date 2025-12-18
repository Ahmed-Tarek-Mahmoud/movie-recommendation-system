package UnitTesting;

import mainPackage.Movie;
import mainPackage.OutputGenerator;
import mainPackage.RecommendationEngine;
import mainPackage.User;
import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class OutputGeneratorTest {
    OutputGenerator outputGen;
    RecommendationEngine recEngine;

    @Before
    public void setup() {
        outputGen = new OutputGenerator();
        recEngine = new RecommendationEngine();
    }

    //Helper function to hash file to string.
    private static String hashFile(String file) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        try (InputStream is = Files.newInputStream(Paths.get(file))) {
            byte[] buffer = new byte[1024];
            int read;
            while ((read = is.read(buffer)) != -1) {
                digest.update(buffer, 0, read);
            }
        }

        // convert hash to hex string
        StringBuilder sb = new StringBuilder();
        for (byte b : digest.digest()) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @Test
    public void printRecommendationTest() throws IOException {
        List<User> users = new ArrayList<>();
        List<Movie> allMovies = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            allMovies.add(new Movie("Movie" + (i + 1), String.valueOf(i + 1), Arrays.asList("Action", "Comedy", "Drama")));
        }

        List<String> likedMovieIds;
        for (int i = 0; i < 3; i++) {
            users.add(new User("User" + (i + 1), String.valueOf(i + 1), Arrays.asList(String.valueOf(i + 1), String.valueOf(i + 2))));
        }

        Map<String, List<String>> movieRecommendations = recEngine.generateRecommendationsForUsers(users, allMovies);


        try (FileWriter outputFileTest = new FileWriter("recommendationsTest.txt")) {
            for (User user : users) {
                String user_id = user.getUserId();
                String user_name = user.getUserName();
                outputFileTest.write("Current User: " + user_name + ", ID: " + user_id + "\n");
                outputFileTest.write("Movie Recommendations: ");
                List<String> userRecommendations = movieRecommendations.get(user_id);
                for (int i = 0; i < userRecommendations.size(); i++) {
                    outputFileTest.write(userRecommendations.get(i));
                    if (i != userRecommendations.size() - 1) outputFileTest.write(", ");
                }
                outputFileTest.write("\n");
            }
        }
        catch (Exception e) {
            System.out.println("Error opening test file");
        }

        outputGen.printRecommendation(users, allMovies);

        try {
            String Expected = hashFile("recommendationsTest.txt");
            String Actual = hashFile("recommendations.txt");
            assertEquals(Actual,Expected);
        } catch (Exception e) {
            System.out.println("Error while hashing files.");
        }

    }

}