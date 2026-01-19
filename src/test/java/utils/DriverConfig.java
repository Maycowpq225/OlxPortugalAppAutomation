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

    private final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();
    private final ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

    public AndroidDriver getDriver() {
        return driver.get();
    }

    public WebDriverWait getWait() {
        return wait.get();
    }

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
            AndroidDriver d = new AndroidDriver(
                    new URI("http://127.0.0.1:4723").toURL(),
                    setUpAllCapabilities()
            );

            this.driver.set(d);
            this.wait.set(new WebDriverWait(d, Duration.ofSeconds(15)));
            this.driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void quitDriver() {
        if (this.driver.get() != null) {
            this.driver.get().quit();
            driver.remove();
        }
        wait.remove();
    }
}
