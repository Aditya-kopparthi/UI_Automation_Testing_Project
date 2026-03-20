package hooks;

import base.BaseTest;
import io.cucumber.java.*;
import utils.ScreenshotUtil;

public class Hooks {

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            ScreenshotUtil.capture(BaseTest.driver, scenario.getName());
        }

        BaseTest.quit();
    }
}