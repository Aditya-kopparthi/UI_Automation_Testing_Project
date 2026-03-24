package hooks;

import factory.DriverFactory;
import io.cucumber.java.*;
import utils.ConfigReader;
import utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;

public class Hooks {

    WebDriver driver;

    @Before
    public void setup() {
        driver = DriverFactory.initDriver();   // ✅ create driver
        driver.get(ConfigReader.get("url"));   // ✅ open URL
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            ScreenshotUtil.capture(driver, scenario.getName());  // ✅ screenshot
        }

        DriverFactory.quitDriver();   // ✅ quit driver
    }
}