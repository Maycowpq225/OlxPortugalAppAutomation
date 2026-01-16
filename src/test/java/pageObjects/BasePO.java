package po;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverConfig;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BasePO {
    public AndroidDriver driver;
    public WebDriverWait wait;

    public BasePO() {
        driver = DriverConfig.shared().driver;
        wait = DriverConfig.shared().wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public WebElement waitElementIsVisible(WebElement element) {
        DriverConfig.shared().wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public WebElement waitElementIsClickable(WebElement element) {
        DriverConfig.shared().wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }
    public WebElement waitElementIsClickableAndClick(WebElement element, int timeoutSeconds) {
        StopWatch timer = new StopWatch();
        timer.start();
        while (timer.getTime(TimeUnit.SECONDS) < timeoutSeconds) {
            try {
                element.click();
                timer.stop();
                return element;
            } catch (ElementClickInterceptedException e) {
                System.out.println("Element not clickable at "
                        + timer.getTime(TimeUnit.SECONDS)
                        + " seconds");
                if (timer.getTime(TimeUnit.SECONDS) > 4) throw e;
            }
        }

        return element;
    }

    public WebElement findElementByText(String text) {
        return DriverConfig.shared().driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text + "\")"));
    }

    /**
     * Realiza o scroll
     *
     * @param direction Direcao para scroll
     * @param element   Elemento de procura
     */
    //direction”: “up”, “down”, “left, “right”.
    protected void navegar(String direction, WebElement element) {
        int limit = 5;
        HashMap<String, String> scrollObject = new HashMap<>();
        for (int i = 0; i < limit; i++) {
            scrollObject.put("direction", direction);
            driver.executeScript("mobile:scroll", scrollObject);
            if (element.isDisplayed()) {
                break;
            }
        }
    }

    /**
     * Do a scroll
     *
     * @param element element to be searched
     */
    //direction”: “up”, “down”, “left, “right”.
    public void navegar(WebElement element) {
        int limit = 5;
        HashMap<String, String> scrollObject = new HashMap<>();
        for (int i = 0; i < limit; i++) {
            //     scrollObject.put("predicateString", driver.findElement(MobileBy.iOSNsPredicateString("type = 'XCUIElementTypeCell' AND name CONTAINS 'Featured carousel'")));
            driver.executeScript("mobile:scroll", scrollObject);
            if (element.isDisplayed()) {
                break;
            }
        }
    }


    /**
     * Used for scroll down and up
     *
     * @param direction scroll goes up or down
     * @param scrollTimes How many times the scroll will be done
     */
    protected void scrollDirection(String direction, int scrollTimes) {
        int height = driver.manage().window().getSize().getHeight();
        int widthCentral = driver.manage().window().getSize().getWidth() - 30;

        int bottomPosition = (int) (height - (height * 0.3));
        int topPosition = (int) (height - (height * 0.7));

        for (int i = 0; i <= scrollTimes; i++) {
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);
            switch (direction.toLowerCase()){
                case "up" -> {
                    swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), widthCentral, topPosition)); // start point
                    swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                    swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), widthCentral, bottomPosition)); // end point
                    swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
                }
                case "down" -> {
                    swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), widthCentral, bottomPosition)); // start point
                    swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                    swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), widthCentral, topPosition)); // end point
                    swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
                }
            }
            driver.perform(Collections.singletonList(swipe));
        }
    }
}
