package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;
import factory.DriverFactory;

public class AmazonSearchResultsPage {

    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    By results = By.cssSelector("div.s-main-slot div[data-component-type='s-search-result']");

    public boolean isResultsPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(results)).isDisplayed();
    }

    public boolean verifyResultsContain(String product) {

        List<WebElement> list = driver.findElements(results);

        for (WebElement e : list) {
            if (e.getText().toLowerCase().contains(product.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}