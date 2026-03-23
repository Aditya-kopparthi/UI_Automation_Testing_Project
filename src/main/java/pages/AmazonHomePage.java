package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class AmazonHomePage {

    WebDriver driver;
    WebDriverWait wait;

    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By searchBox = By.id("twotabsearchtextbox");
    By suggestions = By.xpath("//div[@role='listbox']//div[contains(@class,'s-suggestion')]");

    public void enterSearchText(String text) {
        WebElement box = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        box.clear();
        box.sendKeys(text);
    }

    public void clickFirstSuggestion() {

        By suggestions = By.xpath("//div[@role='button' and contains(@class,'s-suggestion')]");

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(suggestions));

            List<WebElement> list = driver.findElements(suggestions);

            if (!list.isEmpty()) {
                list.get(0).click();   // ✅ Click first suggestion
            } else {
                driver.findElement(searchBox).sendKeys(Keys.ENTER);
            }

        } catch (Exception e) {
            driver.findElement(searchBox).sendKeys(Keys.ENTER);
        }
    }
}