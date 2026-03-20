package utils;

import org.openqa.selenium.*;
import java.io.File;
import java.nio.file.Files;

public class ScreenshotUtil {

    public static String capture(WebDriver driver, String name) {

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String path = "screenshots/" + name + ".png";

            File dest = new File(path);
            Files.copy(src.toPath(), dest.toPath());

            return path;

        } catch (Exception e) {
            return null;
        }
    }
}