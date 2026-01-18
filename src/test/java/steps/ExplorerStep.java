package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.ExplorerPO;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExplorerStep {

    ExplorerPO explorerPO = new ExplorerPO();

    @Given("the app is open and ready to use")
    public void appOpenAndReady() {
        explorerPO.prepareAppToBeUsed();
    }

    @When("the user is on the Explore Page")
    public void the_user_is_on_the_explore_page() {
        explorerPO.olxAppIsOpenAndReady();
    }

    @When("the user selects a recommended post")
    public void the_user_selects_a_recommended_post() {
        explorerPO.selectRecommendedPost();
    }

    @Then("the app should recommend posts for the user")
    public void the_app_should_recommend_posts_for_the_user() {
        boolean state = explorerPO.postsAreBeingRecommended();
        assertTrue(state, "Posts are being recommended");
    }
}
