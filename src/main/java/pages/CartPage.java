package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import factory.DriverFactory;

public class CartPage {

    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    By subtotal = By.xpath("//div[contains(@class,'sc-subtotal')]");
    By cartCount = By.id("nav-cart-count");
    By cartItems = By.xpath("//div[@data-name='Active Items']");

    public String getSubtotal() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(subtotal)).getText();
    }

    public String getCartCount() {
        return driver.findElement(cartCount).getText();
    }

    public boolean isProductPresent() {
        return driver.findElements(cartItems).size() > 0;
    }
}