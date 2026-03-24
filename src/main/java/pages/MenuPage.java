package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import factory.DriverFactory;

public class MenuPage {

    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    // Locators
    By allMenu = By.id("nav-hamburger-menu");

    // "See more / See all" button
    By seeMore = By.xpath("//a[contains(@class,'hmenu-compressed-btn')]");

    // ✅ Correct locator (anchor tag, not div)
    By toysCategory = By.xpath("//a[@data-menu-id='15']");
    By toysAndGames = By.xpath("//a[text()='Toys & Games']");

    // Scrollable menu container
    By menuContainer = By.id("hmenu-content");

    public void navigateToToysGames() {

        wait.until(ExpectedConditions.elementToBeClickable(allMenu)).click();

        WebElement menu = wait.until(
                ExpectedConditions.visibilityOfElementLocated(menuContainer)
        );

        // Scroll menu
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTop = arguments[0].scrollHeight", menu);

        // Click See More
        try {
            wait.until(ExpectedConditions.elementToBeClickable(seeMore)).click();
        } catch (Exception e) {
            System.out.println("See more not present");
        }

        // Scroll again
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTop = arguments[0].scrollHeight", menu);

        // ✅ Updated locator usage
        WebElement toys = wait.until(
                ExpectedConditions.presenceOfElementLocated(toysCategory)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", toys);

        // JS click
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", toys);

        // Next click
        WebElement toysGames = wait.until(
                ExpectedConditions.visibilityOfElementLocated(toysAndGames)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", toysGames);
    }
}