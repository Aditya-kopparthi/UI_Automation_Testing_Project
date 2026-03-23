package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class AmazonSearchResultsPage {

    WebDriver driver;
    WebDriverWait wait;

    public AmazonSearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By resultsHeader = By.xpath("//span[contains(text(),'results for')]");
    By productTitles = By.xpath("//div[@data-component-type='s-search-result']//h2//span");

    public boolean isResultsPageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(resultsHeader)
        ).isDisplayed();
    }

    public boolean verifyResultsContain(String keyword) {

        List<WebElement> titles = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(productTitles)
        );

        for (WebElement title : titles) {
            if (title.getText().toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}