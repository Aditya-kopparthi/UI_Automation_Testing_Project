package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;
import factory.DriverFactory;

public class CategoryPage {

    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    By sportOutdoor = By.xpath("//span[text()='Sport & Outdoor']");
    By toySportsEquipment = By.xpath("//span[text()='Toy Sports Equipment']");
    By football = By.xpath("//span[text()='Football']");

    By products = By.xpath("//a[contains(@class,'octopus-pc-item-link')]");
    public void navigateToFootball() {

        wait.until(ExpectedConditions.elementToBeClickable(sportOutdoor)).click();
        wait.until(ExpectedConditions.elementToBeClickable(toySportsEquipment)).click();
        wait.until(ExpectedConditions.elementToBeClickable(football)).click();
    }

    public void selectProduct() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(products));

        List<WebElement> list = driver.findElements(products);

        if (!list.isEmpty()) {

            WebElement firstProduct = list.get(0);

            // Scroll (important)
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);", firstProduct);

            // JS click (avoid interception)
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", firstProduct);

        } else {
            throw new RuntimeException("❌ No products found");
        }
    }
}