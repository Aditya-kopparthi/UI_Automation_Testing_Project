package pages;

import org.openqa.selenium.*;
import org.apache.logging.log4j.*;

public class HomePage {

    WebDriver driver;
    private static final Logger log = LogManager.getLogger(HomePage.class);

    By searchBox = By.xpath("//input[@data-automation-id='header-input-search']");
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void search(String product) throws InterruptedException {

        WebElement element = driver.findElement(searchBox);

        for (char c : product.toCharArray()) {
            element.sendKeys(String.valueOf(c));
            Thread.sleep(200);
        }

        element.sendKeys(Keys.ENTER);

        log.info("Searched for product: " + product);
    }
}