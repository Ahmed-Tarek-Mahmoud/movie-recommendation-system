import java.util.ArrayList;
import java.util.List;

public interface MovieValidator {

    default void ValidMovie(Movie movie){
        MovieNameValid(movie.getTitle());
        MovieIdValid(movie.getMovieId(), movie.getTitle());
        MovieGenresValid(movie.getGenres());
    }

    default void MovieIdValid(String movieId , String movieName){
        String[] words = movieName.split(" ");
        StringBuilder idLetters = new StringBuilder();
        for (String word : words) {
            idLetters.append(Character.toUpperCase(word.trim().charAt(0)));
        }
        if(!movieId.startsWith(idLetters.toString()) || movieId.length() != (idLetters.length() + 3))
            throw new AppExceptions("Movie ID letters "+ movieId +" are wrong", ErrorCode.MOVIE_ID_LETTERS_ERROR);

    }

    default void MovieNameValid(String movieName){
        String[] words = movieName.split(" ");
        for(String word: words){
            if(!Character.isUpperCase(word.charAt(0)))
                throw new AppExceptions("Movie Title "+ movieName + " is wrong", ErrorCode.MOVIE_TITLE_ERROR);
        }
    }

    default void MovieGenresValid(List<String> genres){
        if(genres.isEmpty())
            throw new AppExceptions("genre category is empty", ErrorCode.MOVIE_GENRE_ERROR);
    }
}
