package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class DriverConfig {

    private static DriverConfig sharedInstance;
    public AndroidDriver driver;
    public WebDriverWait wait;

    public static synchronized DriverConfig shared() {
        if (sharedInstance == null) {
            sharedInstance = new DriverConfig();
        }
        return sharedInstance;
    }

    private UiAutomator2Options setUpAllCapabilities() {
        return new UiAutomator2Options()
                .setAutomationName("UiAutomator2")
                .setPlatformName("Android")
                .setDeviceName("Android Emulator")
                .setUdid("emulator-5554")
                .setAppPackage("com.fixeads.olxportugal");
    }

    public void defaultConfig() {
        try {
            driver = new AndroidDriver(
                    new URI("http://127.0.0.1:4723").toURL(),
                    setUpAllCapabilities()
            );

            wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
