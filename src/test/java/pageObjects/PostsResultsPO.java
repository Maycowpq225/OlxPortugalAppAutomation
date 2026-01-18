package pageObjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PostsResultsPO extends BasePO {

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"ENCONTRÁMOS\").textContains(\"RESULTADOS\")")
    private WebElement lblEncontramosXResults;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"adListing_adGridCard\")")
    private List<WebElement> listOfPosts;

    public void screenIsDisplayed() {
        waitElementIsVisible(lblEncontramosXResults);
    }

    public boolean listOfPostsIsDisplayed() {
        return !listOfPosts.isEmpty();
    }

    public boolean filtersAreApplied(String category, String subCategory) {
        By locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"" + subCategory + " " + category + "\")");
        return driver.findElement(locator).isDisplayed();
    }

}
