import java.util.ArrayList;
import java.util.List;

public interface MovieValidator {

    default void ValidMovie(Movie movie){
        MovieIdValid(movie.getId(), movie.getTitle());
        MovieNameValid(movie.getTitle());
        MovieGenresValid(movie.getGenre());
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

    }

    default void MovieGenresValid(List<String> genres){

    }
}
