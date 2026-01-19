package pageObjects;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ExplorerPO extends BasePO {

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Anúncios recomendados\")")
    private WebElement lblReccomendedPosts;

    @AndroidFindBy(xpath = "(//android.view.View[@resource-id=\"home_screen\"]/android.view.View/android.view.View[@resource-id=\"adListing_adGridCard\"])")
    private List<WebElement> recommendedCards;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"O que procuras?\")")
    private WebElement fieldWhatYouLookFor;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.fixeads.olxportugal:id/btn_accept_cookies\")")
    private WebElement btnAccept;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.fixeads.olxportugal:id/dismissBtnTop\")")
    private WebElement btnSkip;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\")")
    private WebElement btnWhileUsingTheApp;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Todas as categorias\")")
    private WebElement btnAllCategories;

    public void prepareAppToBeUsed() {
        clickIfElementIsVisible(btnAccept, 15);
        clickIfElementIsVisible(btnSkip, 15);
        clickIfElementIsVisible(btnWhileUsingTheApp, 15);
    }

    public void olxAppIsOpenAndReady() {
        waitElementIsVisible(fieldWhatYouLookFor);
    }

    public void selectRecommendedPost() {
        scrollDirection("down", 1);
        waitElementIsClickableAndClick(recommendedCards.getFirst());
    }

    public boolean postsAreBeingRecommended() {
        scrollDirection("down", 2);
        return recommendedCards.size() > 1;
    }

    public void clicksOnAllCategoriesButton() {
        waitElementIsClickableAndClick(btnAllCategories);
    }
}
