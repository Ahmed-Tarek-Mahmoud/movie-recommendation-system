package Regression;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import UnitTesting.MovieValidatorTest;
import UnitTesting.UserIdValidatorTest;
import UnitTesting.RecommendationEngineTest;
import IntegrationTesting.BottomUp.Level0;
import IntegrationTesting.BottomUp.Level1;

@RunWith(Categories.class)
@Categories.IncludeCategory(Regression.class)
@Suite.SuiteClasses({
    MovieValidatorTest.class,
    Level0.class,
    UserIdValidatorTest.class,
    RecommendationEngineTest.class,
    Level1.class
})

public class RegressionSuiteTest {
}