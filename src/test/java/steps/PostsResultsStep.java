package steps;

import io.cucumber.java.en.Then;
import pageObjects.PostsResultsPO;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostsResultsStep {

    private final PostsResultsPO postsResultsPO = new PostsResultsPO();

    @Then("all posts related to category {string} and subcategory {string} are displayed on Posts Results Page")
    public void allPostsRelatedToTheSelectedCategoryAreDisplayed(String category, String subCategory) {
        postsResultsPO.screenIsDisplayed();
        assertTrue(postsResultsPO.listOfPostsIsDisplayed(), "List not found");
        assertTrue(postsResultsPO.filtersAreApplied(category, subCategory), "Filters not applied");
    }
}
