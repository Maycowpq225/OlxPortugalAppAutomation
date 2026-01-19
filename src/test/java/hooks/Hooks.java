package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverConfig;

public class Hooks {

    @Before
    public void initiateDriver() {
        DriverConfig.shared().defaultConfig();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverConfig.shared().getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "target/image/png", "Screenshot on failure");
        }
        DriverConfig.shared().quitDriver();
    }
}
