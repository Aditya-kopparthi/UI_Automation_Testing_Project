package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;
import factory.DriverFactory;

public class AmazonHomePage {

    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    By searchBox = By.id("twotabsearchtextbox");
    By suggestions = By.xpath("//div[@role='button' and contains(@class,'s-suggestion')]");

    public void enterSearchText(String text) {
        WebElement box = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        box.clear();
        box.sendKeys(text);
    }

    public void clickFirstSuggestion() {

        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(suggestions));

            List<WebElement> list = driver.findElements(suggestions);

            if (!list.isEmpty()) {
                list.get(0).click();
            } else {
                driver.findElement(searchBox).sendKeys(Keys.ENTER);
            }

        } catch (Exception e) {
            driver.findElement(searchBox).sendKeys(Keys.ENTER);
        }
    }
}