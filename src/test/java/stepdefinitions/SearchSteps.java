package stepdefinitions;

import base.BaseTest;
import pages.*;
import utils.*;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class SearchSteps {

    HomePage home;
    SearchResultsPage results;

    @Given("user opens Walmart")
    public void open() {
        BaseTest.init();
        BaseTest.driver.get(ConfigReader.get("url"));
        CaptchaHandler.handle(BaseTest.driver);
    }

    @When("user searches for {string}")
    public void search(String product) throws Exception {

        home = new HomePage(BaseTest.driver);
        home.search(product);

        // 🔥 HANDLE CAPTCHA AGAIN
        Thread.sleep(3000);
        CaptchaHandler.handle(BaseTest.driver);
    }

    @Then("results should be displayed")
    public void validate() {

        results = new SearchResultsPage(BaseTest.driver);

        System.out.println("Title: " + BaseTest.driver.getTitle());
        System.out.println("URL: " + BaseTest.driver.getCurrentUrl());

        Assert.assertTrue(results.isDisplayed());
        Assert.assertTrue(results.isRelevant("iphone"));
    }
}