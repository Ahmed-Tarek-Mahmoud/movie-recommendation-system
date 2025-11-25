import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MovieValidatorTest extends ValidationServiceTest {
    @Test
    public void testMovieIDValid1(){
        String title = "Interstellar";
        String ID = "I001";
        List<String> genres = new ArrayList<String>();
        genres.add("Sci-Fi");
        Movie m1 = addMovie(title , ID , genres);
        validationService.ValidMovie(m1);
    }

    @Test
    public void testMovieIDValid2(){
        String title = "Harry Potter";
        String ID = "HP002";
        List<String> genres = new ArrayList<>();
        genres.add("Fantasy");
        Movie m1 = addMovie(title , ID , genres);
        validationService.ValidMovie(m1);
    }

    @Test
    public void testMovieIDValid3_Uniqueness(){
        String title1 = "Interstellar";
        String ID1 = "I001";
        List<String> genres1 = new ArrayList<String>();
        genres1.add("Sci-Fi");
        Movie m1 = addMovie(title1 , ID1 , genres1);


        String title2 = "Alaaeldin";
        String ID2 = "A002";
        List<String> genres2 = new ArrayList<String>();
        genres2.add("Cartoon");
        Movie m2 = addMovie(title2 , ID2 , genres2);
        validationService.validateMovies();
    }

    @Test
    public void testMovieIDInvalid1_lowercase(){
        String title = "Interstellar";
        String ID = "i001";
        List<String> genres = new ArrayList<String>();
        genres.add("Sci-Fi");
        Movie m1 = addMovie(title , ID , genres);
        assertValidationException(()-> validationService.ValidMovie(m1) ,
                ErrorCode.MOVIE_ID_LETTERS_ERROR,
                "Movie ID letters "+ ID +" are wrong");
    }

    @Test
    public void testMovieIDInvalid2_lowercase(){
        String title = "Harry Potter";
        String ID = "Hp001";
        List<String> genres = new ArrayList<String>();
        genres.add("Fantasy");
        Movie m1 = addMovie(title , ID , genres);
        assertValidationException(()-> validationService.ValidMovie(m1) ,
                ErrorCode.MOVIE_ID_LETTERS_ERROR,
                "Movie ID letters "+ ID +" are wrong");
    }

    @Test
    public void testMovieIDInvalid3_wrongLetters(){
        String title = "Harry Potter";
        String ID = "ab001";
        List<String> genres = new ArrayList<String>();
        genres.add("Fantasy");
        Movie m1 = addMovie(title , ID , genres);
        assertValidationException(()-> validationService.ValidMovie(m1) ,
                ErrorCode.MOVIE_ID_LETTERS_ERROR,
                "Movie ID letters "+ ID +" are wrong");
    }

    @Test
    public void testMovieIDInvalid4_WrongNumberOfDigits_less(){
        String title = "Interstellar";
        String ID = "I01";
        List<String> genres = new ArrayList<String>();
        genres.add("Sci-Fi");
        Movie m1 = addMovie(title , ID , genres);
        assertValidationException(()-> validationService.ValidMovie(m1) ,
                ErrorCode.MOVIE_ID_LETTERS_ERROR,
                "Movie ID letters "+ ID +" are wrong");
    }

    @Test
    public void testMovieIDInvalid5_WrongNumberOfDigits_More(){
        String title = "Interstellar";
        String ID = "I0001";
        List<String> genres = new ArrayList<String>();
        genres.add("Sci-Fi");
        Movie m1 = addMovie(title , ID , genres);
        assertValidationException(()-> validationService.ValidMovie(m1) ,
                ErrorCode.MOVIE_ID_LETTERS_ERROR,
                "Movie ID letters "+ ID +" are wrong");
    }

    @Test
    public void testMovieIDInvalid6_Uniqueness(){
        String title1 = "Interstellar";
        String ID1 = "I001";
        List<String> genres1 = new ArrayList<String>();
        genres1.add("Sci-Fi");
        Movie m1 = addMovie(title1 , ID1 , genres1);


        String title2 = "Alaaeldin";
        String ID2 = "A001";
        List<String> genres2 = new ArrayList<String>();
        genres2.add("Cartoon");
        Movie m2 = addMovie(title2 , ID2 , genres2);
        assertValidationException(() -> validationService.validateMovies(),
                ErrorCode.MOVIE_ID_UNIQUE_ERROR,
                "Movie ID numbers " + ID2 + " aren't unique");
    }

    @Test
    public void testMovieIDInvalid7_Uniqueness2(){
        String title1 = "Interstellar";
        String ID1 = "I001";
        List<String> genres1 = new ArrayList<String>();
        genres1.add("Sci-Fi");
        Movie m1 = addMovie(title1 , ID1 , genres1);


        String title2 = "Harry Potter";
        String ID2 = "HP002";
        List<String> genres2 = new ArrayList<String>();
        genres2.add("Fantasy");
        Movie m2 = addMovie(title2 , ID2 , genres2);

        String title3 = "Alaaeldin";
        String ID3 = "A001";
        List<String> genres3 = new ArrayList<String>();
        genres3.add("Cartoon");
        Movie m3 = addMovie(title3 , ID3 , genres3);

        assertValidationException(() -> validationService.validateMovies(),
                ErrorCode.MOVIE_ID_UNIQUE_ERROR,
                "Movie ID numbers " + ID3 + " aren't unique");
    }



    @Test
    public void testMovieTitleInvalid1(){
        String title = "interstellar";
        String ID = "I001";
        List<String> genres = new ArrayList<String>();
        genres.add("Sci-Fi");
        Movie m1 = addMovie(title , ID , genres);
        assertValidationException(()-> validationService.ValidMovie(m1),
                ErrorCode.MOVIE_TITLE_ERROR,
                "Movie Title "+ title + " is wrong");
    }

    @Test
    public void testMovieTitleInvalid2(){
        String title = "Harry potter";
        String ID = "H001";
        List<String> genres = new ArrayList<String>();
        genres.add("Fantasy");
        Movie m1 = addMovie(title , ID , genres);
        assertValidationException(()-> validationService.ValidMovie(m1),
                ErrorCode.MOVIE_TITLE_ERROR,
                "Movie Title "+ title + " is wrong");
    }

    @Test
    public void testMovieTitleInvalid3(){
        String title = "harry Potter";
        String ID = "P001";
        List<String> genres = new ArrayList<String>();
        genres.add("Fantasy");
        Movie m1 = addMovie(title , ID , genres);
        assertValidationException(()-> validationService.ValidMovie(m1),
                ErrorCode.MOVIE_TITLE_ERROR,
                "Movie Title "+ title + " is wrong");
    }

    @Test
    public void testMovieTitleInvalid4(){
        String title = "harry potter";
        String ID = "H001";
        List<String> genres = new ArrayList<String>();
        genres.add("Fantasy");
        Movie m1 = addMovie(title , ID , genres);
        assertValidationException(()-> validationService.ValidMovie(m1),
                ErrorCode.MOVIE_TITLE_ERROR,
                "Movie Title "+ title + " is wrong");
    }

    @Test
    public void testMovieTitleValid1(){
        String title = "Interstellar";
        String ID = "I001";
        List<String> genres = new ArrayList<String>();
        genres.add("Sci-Fi");
        Movie m1 = addMovie(title , ID , genres);
        validationService.ValidMovie(m1);
    }

    @Test
    public void testMovieTitleValid2(){
        String title = "Harry Potter";
        String ID = "HP001";
        List<String> genres = new ArrayList<String>();
        genres.add("Fantasy");
        Movie m1 = addMovie(title , ID , genres);
        validationService.ValidMovie(m1);
    }

    @Test
    public void testMovieGenreInvalid1(){
        String title = "Interstellar";
        String ID = "I001";
        List<String> genres = new ArrayList<String>();
        Movie m1 = addMovie(title , ID , genres);
        assertValidationException(()-> validationService.ValidMovie(m1),
                ErrorCode.MOVIE_GENRE_ERROR,
                "genre category is empty");
    }
}
