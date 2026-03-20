package base;

import org.openqa.selenium.WebDriver;
import factory.DriverFactory;

public class BaseTest {

    public static WebDriver driver;

    public static void init() {
        driver = DriverFactory.initDriver();
        driver.manage().window().maximize();
    }

    public static void quit() {
        driver.quit();
    }
}