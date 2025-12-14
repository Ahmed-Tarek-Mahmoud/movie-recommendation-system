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

    @Test
    void userName_startingWithSpace_shouldFail() {
        User user = new User(
                " Ahmed",
                "12345678A",
                List.of()
        );

        // Expected trimmed name, actual has leading space → FAIL
        assertEquals("Ahmed", user.getUserName());
    }

    @Test
    void userName_withNumbers_shouldFail() {
        User user = new User(
                "Ahmed123",
                "12345678A",
                List.of()
        );

        // Expected name without numbers → FAIL
        assertEquals("Ahmed", user.getUserName());
    }

    @Test
    void userId_wrongLength_shouldFail() {
        User user = new User(
                "Ahmed Ali",
                "12345",
                List.of()
        );

        // Expected valid 9-char ID → FAIL
        assertEquals("123456789", user.getUserId());
    }

    @Test
    void userId_notStartingWithNumber_shouldFail() {
        User user = new User(
                "Ahmed Ali",
                "A2345678A",
                List.of()
        );

        // Expected numeric start → FAIL
        assertEquals("12345678A", user.getUserId());
    }

    @Test
    void userId_endingWithMoreThanOneLetter_shouldFail() {
        User user = new User(
                "Ahmed Ali",
                "1234567AB",
                List.of()
        );

        // Expected only one ending letter → FAIL
        assertEquals("12345678A", user.getUserId());
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
    @Test
    void likedMovies_lowercaseLetters_shouldFail() {
        User user = new User(
                "Ahmed Ali",
                "12345678A",
                List.of("avn123")
        );

        // Expected uppercase → actual is lowercase → FAIL
        assertEquals("AVN123", user.getLikedMovieIds().get(0));
    }

    @Test
    void likedMovies_nonUniqueDigits_shouldFail() {
        User user = new User(
                "Ahmed Ali",
                "12345678A",
                List.of("SPM111")
        );

        // Expected unique digits → actual has repeated digits → FAIL
        assertEquals("SPM123", user.getLikedMovieIds().get(0));
    }
}

