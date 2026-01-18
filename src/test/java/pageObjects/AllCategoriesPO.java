package pageObjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AllCategoriesPO extends BasePO {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.fixeads.olxportugal:id/toolbarLayout\").descriptionContains(\"Escolher categoria\")")
    private WebElement lblChooseCategorie;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Tudo em\")")
    private WebElement btnEveryThingIn;

    public void screenIsDisplayed() {
        waitElementIsVisible(lblChooseCategorie);
    }

    public boolean categoryIsDisplayed(String category) {
        By locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"" + category + "\")");
        return scrollToElement(locator, 5).isDisplayed();
    }

    public void clickOnCategory(String category) {
        By locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"" + category + "\")");
        scrollToElement(locator, 5).click();
    }

    public void clickBtnAllCategoriesOnSubCategories() {
        waitElementIsVisible(btnEveryThingIn).click();
    }
}
