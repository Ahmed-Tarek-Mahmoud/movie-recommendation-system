package IntegrationTesting.LevelTwo;

import mainPackage.Movie;
import mainPackage.OutputGenerator;
import mainPackage.RecommendationEngine;
import mainPackage.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class RecommendationEngineTest {

    private RecommendationEngine recommendationEngine;
    private List<User> users;
    Map<String, Movie> allMovies;

    @Before
    public void setupLists(){
        allMovies = new HashMap<>();
        users = new ArrayList<>();
    }


    @Test
    public void testGenerateRecommendationsIsCalled (){
        users.add(new User("Adnan", "123456789", List.of("TM001","I002")));

        RecommendationEngine recommendationEngine = spy(new RecommendationEngine());

        doReturn(new ArrayList<>())
                .when(recommendationEngine)
                .generateRecommendations(anyList(), anyMap());

        recommendationEngine.generateRecommendationsForUsers(users, allMovies);

        verify(recommendationEngine, times(1))
                .generateRecommendations(anyList(), anyMap());
    }
}
