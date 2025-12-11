package mainPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FileParser {

    public static void loadUsers(String filePath, Map<String, User> users) throws IOException {
        /**
         * Loads user data from a text file and stores it in the provided map.
         * Each user is represented by two lines:
         *      Line 1: userName,userId
         *      Line 2: comma-separated liked movie IDs
         *
         * @param filePath the path to the users file
         * @param users    a map where userId → mainPackage.User object will be stored
         * @throws IOException if the file cannot be read
         */
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = br.readLine()) != null) {

            String[] parts = line.split(",");
            String name = parts[0].trim();
            String id = parts[1].trim();


            String likedLine = br.readLine();
            List<String> likedMovies = Arrays.stream(likedLine.split(","))
                    .map(String::trim)
                    .toList();

            User u = new User(name,id,likedMovies);

            users.put(id, u);
        }
        br.close();
    }

    public static void loadMovies(String filePath, Map<String, Movie> movies) throws IOException {
        /**
         * Loads movie data from a text file and stores it in the provided map.
         * Each movie is represented by two lines:
         *      Line 1: movieName,movieId
         *      Line 2: comma-separated genre names
         *
         * @param filePath the path to the movies file
         * @param movies   a map where movieId → Movie object will be stored
         * @throws IOException if the file cannot be read
         */
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = br.readLine()) != null) {

            String[] parts = line.split(",");
            String movie_name = parts[0].trim();
            String movie_id = parts[1].trim();

            String likedLine = br.readLine();
            List<String> moive_genres = Arrays.stream(likedLine.split(","))
                    .map(String::trim)
                    .toList();

            Movie m = new Movie(movie_name,movie_id,moive_genres);
            movies.put(movie_id,m);

        }
    }
}