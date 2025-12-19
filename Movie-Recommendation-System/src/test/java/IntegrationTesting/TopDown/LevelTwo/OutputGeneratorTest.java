package IntegrationTesting.LevelTwo;

import mainPackage.OutputGenerator;
import mainPackage.RecommendationEngine;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.*;

public class OutputGeneratorTest {


    @Test
    void testGenerateRecommendationsForUsersIsCalled() throws IOException {

        RecommendationEngine engine = mock(RecommendationEngine.class);
        OutputGenerator outputGenerator = new OutputGenerator(engine);

        outputGenerator.printRecommendation(anyList(), anyMap());

        verify(engine, times(1))
                .generateRecommendationsForUsers(anyList(), anyMap());
    }

}
