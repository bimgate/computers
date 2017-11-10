package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by bratislav.miletic on 09/11/2017.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/",
        glue = "com.backbase.qa.steps",
        strict = true,
        plugin = {"html:target/cucumber-report"}
)

public class TestRunner {


}
