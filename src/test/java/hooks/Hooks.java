package hooks;

import factory.DriverFactory;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ConfigReader;
import utils.ScreenshotUtil;
import org.openqa.selenium.*;

public class Hooks {

    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(Hooks.class);

    @Before
    public void setup(Scenario scenario) {

        log.info("========== TEST STARTED: " + scenario.getName() + " ==========");

        // Initialize driver
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();

        // Open URL
        driver.get(ConfigReader.get("url"));

        log.info("Browser launched and URL opened");
    }

    @After
    public void tearDown(Scenario scenario) {

        try {
            if (scenario.isFailed()) {

                log.error("TEST FAILED: " + scenario.getName());

                // 📸 Capture screenshot (for report)
                byte[] screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);

                scenario.attach(screenshot, "image/png", scenario.getName());

                // 📁 Save screenshot locally
                ScreenshotUtil.capture(driver, scenario.getName());

            } else {
                log.info("TEST PASSED: " + scenario.getName());
            }

        } catch (Exception e) {
            log.error("Error in teardown: ", e);
        } finally {

            // Quit driver safely
            DriverFactory.quitDriver();

            log.info("Browser closed");
            log.info("========== TEST ENDED ==========\n");
        }
    }
}