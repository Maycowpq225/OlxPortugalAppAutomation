package pageObjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverConfig;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BasePO {
    public AndroidDriver driver;
    public WebDriverWait wait;

    public BasePO() {
        driver = DriverConfig.shared().driver;
        wait = DriverConfig.shared().wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickIfElementIsVisible(WebElement element, int waitingSeconds) {
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitingSeconds));
        try {
            element.click();
        } catch (NoSuchElementException ex) {
            System.out.println("Button not found.");
        }
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
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

    public void waitElementIsClickableAndClick(WebElement element) {
        DriverConfig.shared().wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public WebElement findElementByText(String text) {
        return DriverConfig.shared().driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text + "\")"));
    }

    /**
     * Used for scroll down and up
     *
     * @param direction   scroll goes up or down
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
            switch (direction.toLowerCase()) {
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

    protected void scrollDownPercent(int quantidade, int percent0A100) {
        Dimension size = driver.manage().window().getSize();
        int height = size.getHeight();
        int widthCentral = size.getWidth() - 30;

        int posicaoInicial = (int) (height * 0.5 - (((height * 0.5) * percent0A100) / 100));
        int posicaoFinal = (int) (height * 0.5 + (((height * 0.3) * percent0A100) / 100));

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        for (int i = 0; i < quantidade; i++) {
            Sequence swipe = new Sequence(finger, 1);

            //Move the finger to final position (without touching the screen)
            swipe.addAction(finger.createPointerMove(
                    Duration.ZERO,
                    PointerInput.Origin.viewport(),
                    widthCentral,
                    posicaoFinal
            ));

            //Touch the screen and keep the finger there
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

            //Move the finger to initial position (touching the screen)
            swipe.addAction(finger.createPointerMove(
                    Duration.ofMillis(600),
                    PointerInput.Origin.viewport(),
                    widthCentral,
                    posicaoInicial
            ));

            //Release the finger
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            //Execute the sequence
            driver.perform(List.of(swipe));
        }
    }

    public WebElement scrollToElement(WebElement element, int secondsSeekingElement) {
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        StopWatch timer = new StopWatch();
        timer.start();
        while (timer.getTime(TimeUnit.SECONDS) < secondsSeekingElement) {
            try {
                element.isDisplayed();
                timer.stop();
                this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
                return element;
            } catch (NoSuchElementException e) {
                scrollDownPercent(1, 50);
            }
        }
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        throw new NoSuchElementException("Element not found");
    }

    public WebElement scrollToElement(By locator, int secondsSeekingElement) {
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        StopWatch timer = new StopWatch();
        timer.start();
        while (timer.getTime(TimeUnit.SECONDS) < secondsSeekingElement) {
            try {
                WebElement element = driver.findElement(locator);
                timer.stop();
                this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
                return element;
            } catch (NoSuchElementException e) {
                scrollDownPercent(1, 50);
            }
        }
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        throw new NoSuchElementException("Element not found");
    }
}
