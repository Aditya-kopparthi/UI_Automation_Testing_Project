package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class CaptchaHandler {

    private static final int MAX_RETRIES = 5;

    public static void handle(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {

            try {
                System.out.println("Captcha attempt: " + attempt);

                List<WebElement> frames = driver.findElements(By.tagName("iframe"));

                boolean captchaHandled = false;

                for (WebElement frame : frames) {

                    driver.switchTo().frame(frame);

                    List<WebElement> buttons = driver.findElements(
                            By.xpath("//button[contains(text(),'PRESS')]")
                    );

                    if (!buttons.isEmpty()) {

                        WebElement button = wait.until(
                                ExpectedConditions.elementToBeClickable(buttons.get(0))
                        );

                        Actions actions = new Actions(driver);

                        actions.clickAndHold(button)
                                .pause(Duration.ofSeconds(10))
                                .release()
                                .perform();

                        System.out.println("Captcha handled successfully");

                        captchaHandled = true;
                        driver.switchTo().defaultContent();

                        break;
                    }

                    driver.switchTo().defaultContent();
                }

                if (!captchaHandled) {
                    System.out.println("Captcha not found in this attempt");
                    break;
                }

                // Wait to check if captcha appears again
                Thread.sleep(3000);

            } catch (Exception e) {
                System.out.println("Retrying captcha... " + e.getMessage());
            }
        }
    }
}