package mainPackage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class OutputGenerator {

    public void printRecommendation (List<User> users, List<Movie> allMovies) throws IOException {
        RecommendationEngine recEngine = new RecommendationEngine();
        Map<String, List<String>> movieRecommendations = recEngine.generateRecommendationsForUsers(users, allMovies);

        try {
            FileWriter outputFile = new FileWriter ("recommendations.txt");
            for(User user : users) {
                String user_id = user.getUserId();
                String user_name = user.getUserName();
                outputFile.write("Current User: " + user_name + ", ID: " + user_id + "\n");
                outputFile.write("Movie Recommendations: ");
                List<String> userRecommendations = movieRecommendations.get(user_id);
                for (int i=0; i<userRecommendations.size(); i++) {
                    outputFile.write(userRecommendations.get(i));
                    if (i != userRecommendations.size() - 1) outputFile.write(", ");
                }
                outputFile.write("\n");
            }
            outputFile.close();

        } catch (Exception e) {
            System.out.println("An error has occurred while opening output file.");
        }

    }

}

