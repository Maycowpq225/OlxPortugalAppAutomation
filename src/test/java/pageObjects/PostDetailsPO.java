package pageObjects;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PostDetailsPO extends BasePO {


    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.fixeads.olxportugal:id/adImagePager\")")
    private WebElement imgPost;

    @AndroidFindBy(uiAutomator =
                    "new UiSelector()"
                            + ".resourceId(\"com.fixeads.olxportugal:id/ad_details_price_title\")"
                            + ".childSelector(new UiSelector()"
                            + "    .className(\"android.view.View\")"
                            + "    .childSelector(new UiSelector()"
                            + "        .className(\"android.view.View\")"
                            + "        .childSelector(new UiSelector()"
                            + "            .className(\"android.widget.TextView\")"
                            + "        )"
                            + "    )"
                            + ")"
    )
    private WebElement lblTitlePost;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"€\")")
    private WebElement lblPricePost;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Descrição\")")
    private WebElement lblDescriptionPost;

    @AndroidFindBy(uiAutomator =
            "new UiSelector()"
                    + ".className(\"android.widget.TextView\")"
                    + ".text(\"DESCRIÇÃO\")"
                    + ".fromParent(new UiSelector()"
                    + "    .className(\"android.view.View\")"
                    + "    .childSelector(new UiSelector()"
                    + "        .className(\"android.widget.TextView\")"
                    + "    )"
                    + ")"
    )
    private WebElement textDescriptionPost;


    public boolean postDetailsIsDisplayed() {
        return waitElementIsVisible(imgPost).isDisplayed()
                && lblTitlePost.isDisplayed()
                && lblPricePost.isDisplayed()
                && lblDescriptionPost.isDisplayed()
                && textDescriptionPost.isDisplayed();
    }

}
