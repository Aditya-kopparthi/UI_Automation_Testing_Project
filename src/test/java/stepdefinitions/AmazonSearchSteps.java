package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.AmazonHomePage;
import pages.AmazonSearchResultsPage;
import factory.DriverFactory;

public class AmazonSearchSteps {

    WebDriver driver = DriverFactory.initDriver();

    AmazonHomePage homePage = new AmazonHomePage(driver);
    AmazonSearchResultsPage resultsPage = new AmazonSearchResultsPage(driver);

    @Given("user opens Amazon homepage")
    public void openAmazon() {
        driver.get("https://www.amazon.in");
    }

    @When("user enters {string} in search bar")
    public void enterSearch(String product) {
        homePage.enterSearchText(product);
    }

    @And("user clicks on first suggestion")
    public void clickFirstSuggestion() {
        homePage.clickFirstSuggestion();
    }

    @Then("results page should be displayed")
    public void verifyResultsPage() {
        Assert.assertTrue(resultsPage.isResultsPageDisplayed());
    }

    @And("results should be relevant to {string}")
    public void validateResults(String product) {
        Assert.assertTrue(resultsPage.verifyResultsContain(product));
    }
}