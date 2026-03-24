package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.Set;
import factory.DriverFactory;

public class ProductPage {

    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    By productTitle = By.id("productTitle");
    By addToCart = By.id("add-to-cart-button");
    By goToCartBtn = By.xpath("//a[contains(text(),'Go to Cart')]");

    public void switchToTab() {

        Set<String> handles = driver.getWindowHandles();

        for (String handle : handles) {
            driver.switchTo().window(handle);
        }
    }

    public boolean isProductPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle)).isDisplayed();
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCart)).click();
    }

    public void goToCart() {

        try {
            wait.until(ExpectedConditions.elementToBeClickable(goToCartBtn)).click();
        } catch (Exception e) {
            // fallback
            driver.get("https://www.amazon.in/gp/cart/view.html");
        }
    }
}