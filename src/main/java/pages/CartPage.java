package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import factory.DriverFactory;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;

    public CartPage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ================= LOCATORS =================

    // "Added to Cart" confirmation page
    By addedToCartMsg = By.xpath("//h1[contains(text(),'Added to Cart')]");

    // Subtotal price (works for most flows)
    By subtotalWhole = By.xpath("//span[@class='a-price-whole']");
    By subtotalContainer = By.id("sc-subtotal-amount-activecart");

    // Cart count (top right icon)
    By cartCount = By.id("nav-cart-count");

    // Cart icon (to navigate explicitly)
    By cartButton = By.id("nav-cart");

    // Cart page items (actual cart page)
    By cartItems = By.xpath("//div[contains(@class,'sc-list-item')]");

    // ================= ACTIONS =================

    // Navigate to cart page explicitly
    public void goToCartPage() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
        } catch (Exception e) {
            // fallback (important for flaky flows)
            driver.get("https://www.amazon.in/gp/cart/view.html");
        }
    }

    // ================= VALIDATIONS =================

    // ✅ Check product added (works for both flows)
    public boolean isProductPresent() {
        try {
            // Case 1: "Added to Cart" confirmation page
            if (driver.findElements(addedToCartMsg).size() > 0) {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(addedToCartMsg)).isDisplayed();
            }

            // Case 2: Actual cart page items
            return wait.until(ExpectedConditions.visibilityOfElementLocated(cartItems)).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Get subtotal (handles multiple DOM variations)
    public String getSubtotal() {
        try {
            // Try main subtotal container
            if (driver.findElements(subtotalContainer).size() > 0) {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(subtotalContainer)).getText();
            }

            // Fallback to price element
            return wait.until(ExpectedConditions.visibilityOfElementLocated(subtotalWhole)).getText();

        } catch (Exception e) {
            return "Subtotal not found";
        }
    }

    // ✅ Get cart count (with wait)
    public String getCartCount() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(cartCount)).getText();
        } catch (Exception e) {
            return "0";
        }
    }

    // ✅ Verify subtotal contains currency
    public boolean isSubtotalDisplayed() {
        String subtotal = getSubtotal();
        return subtotal.contains("₹") || subtotal.matches(".*\\d+.*");
    }

    // ✅ Verify cart count updated
    public boolean isCartCountUpdated() {
        try {
            int count = Integer.parseInt(getCartCount());
            return count > 0;
        } catch (Exception e) {
            return false;
        }
    }
}