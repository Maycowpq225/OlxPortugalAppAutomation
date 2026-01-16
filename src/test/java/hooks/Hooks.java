package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverConfig;

public class Hooks {

    @Before
    public static void initiateDriver() {
        DriverConfig.shared().defaultConfig();
    }

    @After
    public static void tearDown() {
        //DriverConfig.shared().quitDriver();
    }
}
