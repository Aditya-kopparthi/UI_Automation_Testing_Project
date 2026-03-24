package runners;

import io.cucumber.testng.*;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features/add_to_cart.feature",
        glue = {"stepdefinitions", "hooks"},
        plugin = {"pretty", "html:target/report.html"},
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

        @Override
        @DataProvider(parallel = false)
        public Object[][] scenarios() {
                return super.scenarios();
        }
}