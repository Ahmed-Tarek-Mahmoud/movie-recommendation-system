package mainPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileParser {

    public static List<User> loadUsers(String filePath) throws IOException {
        /**
         * Loads user data from a text file and returns a list of users.
         * Each user is represented by two lines:
         *      Line 1: userName,userId
         *      Line 2: comma-separated liked movie IDs
         *
         * @param filePath the path to the users file
         * @return List of User objects loaded from the file
         * @throws IOException if the file cannot be read
         */
        List<User> users = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = br.readLine()) != null) {

            String[] parts = line.split(",");
            if (parts.length < 2)
                continue;
            String name = parts[0].trim();
            String id = parts[1].trim();


            String likedLine = br.readLine();
            List<String> likedMovies = Arrays.stream(likedLine.split(","))
                    .map(String::trim)
                    .toList();

            User u = new User(name,id,likedMovies);

            users.add(u);
        }
        br.close();
        return users;
    }

    public static List<Movie> loadMovies(String filePath) throws IOException {
        /**
         * Loads movie data from a text file and returns a list of movies.
         * Each movie is represented by two lines:
         *      Line 1: movieName,movieId
         *      Line 2: comma-separated genre names
         *
         * @param filePath the path to the movies file
         * @return List of Movie objects loaded from the file
         * @throws IOException if the file cannot be read
         */
        List<Movie> movies = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = br.readLine()) != null) {

            String[] parts = line.split(",");
            if (parts.length < 2)
                continue;
            String movie_name = parts[0].trim();
            String movie_id = parts[1].trim();

            String likedLine = br.readLine();
            List<String> moive_genres = Arrays.stream(likedLine.split(","))
                    .map(String::trim)
                    .toList();

            Movie m = new Movie(movie_name,movie_id,moive_genres);
            movies.add(m);

        }
        br.close();
        return movies;
    }
}