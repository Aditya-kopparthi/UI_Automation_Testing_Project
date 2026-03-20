package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.List;

public class CaptchaHandler {

    public static void handle(WebDriver driver) {

        try {
            List<WebElement> frames = driver.findElements(By.tagName("iframe"));

            for (WebElement frame : frames) {

                driver.switchTo().frame(frame);

                List<WebElement> buttons =
                        driver.findElements(By.xpath("//button[contains(text(),'PRESS')]"));

                if (!buttons.isEmpty()) {

                    Actions actions = new Actions(driver);

                    actions.clickAndHold(buttons.get(0))
                            .pause(Duration.ofSeconds(5))
                            .release()
                            .perform();

                    driver.switchTo().defaultContent();
                    return;
                }

                driver.switchTo().defaultContent();
            }

        } catch (Exception e) {
            System.out.println("Captcha not present");
        }
    }
}