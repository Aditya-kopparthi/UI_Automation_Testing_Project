package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.apache.logging.log4j.*;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage {

    WebDriver driver;
    WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(SearchResultsPage.class);

    By results = By.xpath("//div[@data-item-id]");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isDisplayed() {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-item-id]")),
                    ExpectedConditions.titleContains("iPhone")
            ));

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRelevant(String keyword) {

        List<WebElement> items = driver.findElements(results);

        for (WebElement item : items) {
            if (item.getText().toLowerCase().contains(keyword.toLowerCase())) {
                log.info("Relevant product found");
                return true;
            }
        }
        return false;
    }
}