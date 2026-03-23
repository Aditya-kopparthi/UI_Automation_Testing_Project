package hooks;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setup() {
        DriverFactory.initDriver();   // ✅ create driver
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();   // ✅ close driver
    }
}