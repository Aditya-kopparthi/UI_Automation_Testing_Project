package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AmazonCartSteps {

    private static final Logger log = LogManager.getLogger(AmazonCartSteps.class);

    MenuPage menu = new MenuPage();
    CategoryPage category = new CategoryPage();
    ProductPage product = new ProductPage();
    CartPage cart = new CartPage();

    @Given("user is on Amazon homepage")
    public void openAmazon() {
        log.info("Amazon homepage opened");
        // Already handled in Hooks
    }

    @When("user navigates to Toys category from menu")
    public void navigateMenu() {
        log.info("Navigating to Toys category");
        menu.navigateToToysGames();
    }

    @And("user selects a product from category page")
    public void selectProduct() {
        log.info("Selecting product");
        category.navigateToFootball();   // includes filters
        category.selectProduct();
    }

    @And("user switches to product tab")
    public void switchTab() {
        log.info("Switching to product tab");
        product.switchToTab();
    }

    @Then("product page should be displayed")
    public void validateProductPage() {
        log.info("Validating product page");
        Assert.assertTrue(product.isProductPageDisplayed(),
                "❌ Product page is not displayed");
    }

    @When("user adds product to cart")
    public void addCart() {
        log.info("Adding product to cart");
        product.addToCart();
    }

    @And("user navigates to cart page")
    public void openCart() {
        log.info("Opening cart page");
        product.goToCart();
    }

    @Then("product should be added to cart")
    public void validateProductAdded() {
        log.info("Validating product added");
        Assert.assertTrue(cart.isProductPresent(),
                "❌ Product not added to cart");
    }

    @And("subtotal should be displayed")
    public void validateSubtotal() {
        String subtotal = cart.getSubtotal();
        log.info("Subtotal: " + subtotal);

        Assert.assertTrue(subtotal.contains("₹"),
                "❌ Subtotal not displayed correctly");
    }

    @And("cart count should be updated")
    public void validateCartCount() {
        int count = Integer.parseInt(cart.getCartCount());
        log.info("Cart count: " + count);

        Assert.assertTrue(count > 0,
                "❌ Cart count not updated");
    }
}