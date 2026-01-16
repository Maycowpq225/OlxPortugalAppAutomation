package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.ExplorerPO;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExplorerSteps {

    ExplorerPO explorerPO = new ExplorerPO();

    @Given("the app is open and ready to use")
    public void appOpenAndReady() {
        explorerPO.prepareAppToBeUsed();
    }

    @When("the user is on the Explore Page")
    public void the_user_is_on_the_explore_page() {
        explorerPO.olxAppIsOpenAndReady();
    }

    @Then("the app should reccomend posts for the user")
    public void the_app_should_reccomend_posts_for_the_user() {
        boolean state = explorerPO.postsAreBeingReccomended();
        assertTrue(state, "Posts are being recommended");
    }
}
