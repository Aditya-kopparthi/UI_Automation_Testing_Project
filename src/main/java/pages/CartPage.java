package pages;

import org.openqa.selenium.*;
import java.util.List;
import factory.DriverFactory;
import utils.WaitUtil;

public class CartPage {

    WebDriver driver;

    public CartPage() {
        this.driver = DriverFactory.getDriver();
    }

    // ================= LOCATORS =================

    By addedToCartMsg = By.xpath("//h1[contains(text(),'Added to Cart')]");
    By subtotalWhole = By.xpath("//span[@class='a-price-whole']");
    By subtotalContainer = By.id("sc-subtotal-amount-activecart");
    By cartCount = By.id("nav-cart-count");
    By cartButton = By.id("nav-cart");
    By cartItems = By.xpath("//div[contains(@class,'sc-list-item')]");

    // ================= ACTIONS =================

    public void goToCartPage() {
        try {
            WaitUtil.click(driver, cartButton);
        } catch (Exception e) {
            driver.get("https://www.amazon.in/gp/cart/view.html");
        }
    }

    // ================= VALIDATIONS =================

    public boolean isProductPresent() {
        try {
            // Case 1: Added to cart page
            List<WebElement> addedMsg = driver.findElements(addedToCartMsg);
            if (!addedMsg.isEmpty()) {
                return WaitUtil.waitForElement(driver, addedToCartMsg).isDisplayed();
            }

            // Case 2: Cart page items
            return WaitUtil.waitForElement(driver, cartItems).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public String getSubtotal() {
        try {
            // Try main subtotal container
            if (!driver.findElements(subtotalContainer).isEmpty()) {
                return WaitUtil.waitForElement(driver, subtotalContainer).getText();
            }

            // Fallback
            return WaitUtil.waitForElement(driver, subtotalWhole).getText();

        } catch (Exception e) {
            return "Subtotal not found";
        }
    }

    public String getCartCount() {
        try {
            return WaitUtil.waitForElement(driver, cartCount).getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public boolean isSubtotalDisplayed() {
        String subtotal = getSubtotal();
        return subtotal.contains("₹") || subtotal.matches(".*\\d+.*");
    }

    public boolean isCartCountUpdated() {
        try {
            int count = Integer.parseInt(getCartCount());
            return count > 0;
        } catch (Exception e) {
            return false;
        }
    }
}