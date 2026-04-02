package utils;

import org.openqa.selenium.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtil {

    public static String capture(WebDriver driver, String name) {

        try {
            // Clean file name (VERY IMPORTANT)
            name = name.replaceAll("[^a-zA-Z0-9]", "_");

            // Create folder if not exists
            String dir = System.getProperty("user.dir") + "/reports/screenshots/";
            Files.createDirectories(Paths.get(dir));

            // Take screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String path = dir + name + ".png";

            // Copy file
            Files.copy(src.toPath(), Paths.get(path));

            System.out.println("Screenshot saved: " + path);

            return path;

        } catch (Exception e) {
            e.printStackTrace();   // ✅ VERY IMPORTANT
            return null;
        }
    }
}