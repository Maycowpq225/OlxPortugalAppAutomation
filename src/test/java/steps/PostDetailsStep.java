package steps;

import io.cucumber.java.en.Then;
import pageObjects.PostDetailsPO;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostDetailsStep {

    PostDetailsPO postDetailsPO = new PostDetailsPO();

    @Then("the post details page is displayed")
    public void postDetailsIsDisplayed() {
        boolean state = postDetailsPO.postDetailsIsDisplayed();
        assertTrue(state, "Post details is displayed successfully");
    }
}
