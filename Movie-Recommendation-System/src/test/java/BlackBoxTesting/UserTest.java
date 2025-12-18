package mainPackage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserTest {



    @Test
    void validUser_shouldPass() {
        User user = new User(
                "Ahmed Ali",
                "12345678A",
                List.of("M1", "M2")
        );

        assertEquals("Ahmed Ali", user.getUserName());
        assertEquals("12345678A", user.getUserId());
    }

    @Test
    void validUserId_withoutEndingLetter_shouldPass() {
        User user = new User(
                "Mohamed Hassan",
                "123456789",
                List.of()
        );

        assertEquals("123456789", user.getUserId());
    }
    @Test
    void validUserName_withSpaces_shouldPass() {
        User user = new User(
                "Omar Abdel Rahman",
                "987654321",
                List.of("M10", "M20")
        );

        assertEquals("Omar Abdel Rahman", user.getUserName());
    }

    @Test
    void validUserId_endingWithOneLetter_shouldPass() {
        User user = new User(
                "Youssef Mahmoud",
                "87654321Z",
                List.of()
        );

        assertEquals("87654321Z", user.getUserId());
    }







    //---------------------------
    // testing the Movie liked list
    // ---------------------------
    @Test
    void likedMovies_validIds_shouldPass() {
        User user = new User(
                "Ahmed Ali",
                "12345678A",
                List.of("AVN123", "SPM907")
        );

        assertEquals(List.of("AVN123", "SPM907"), user.getLikedMovieIds());
    }

    @Test
    void likedMovies_singleValidId_shouldPass() {
        User user = new User(
                "Omar Hassan",
                "987654321",
                List.of("BAT456")
        );

        assertEquals("BAT456", user.getLikedMovieIds().get(0));
    }
    
}

