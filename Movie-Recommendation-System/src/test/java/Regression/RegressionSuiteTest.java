package Regression;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import UnitTesting.MovieValidatorTest;
import UnitTesting.FileParserTest;
import UnitTesting.UserIdValidatorTest;
import UnitTesting.RecommendationEngineTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(Regression.class)
@Suite.SuiteClasses({
    MovieValidatorTest.class,
//  FileParserTest.class,
    UserIdValidatorTest.class,
    RecommendationEngineTest.class
})

public class RegressionSuiteTest {
}