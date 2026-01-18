package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AllCategoriesPO;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllCategoriesStep {

    AllCategoriesPO allCategoriesPO = new AllCategoriesPO();

    @When("the user clicks on the category {string} on All Categories Page")
    public void clickOnCategory(String category) {
        allCategoriesPO.clickOnCategory(category);
    }

    @When("the user clicks on all the categories option on SubCategories")
    public void clickOnAllCategories() {
        allCategoriesPO.clickBtnAllCategoriesOnSubCategories();
    }

    @Then("all available categories are displayed to the user")
    public void allCategoriesDisplayed() {
        allCategoriesPO.screenIsDisplayed();
        assertTrue(allCategoriesPO.categoryIsDisplayed("Todas as categorias"));
        assertTrue(allCategoriesPO.categoryIsDisplayed("Carros, motos e barcos"));
        assertTrue(allCategoriesPO.categoryIsDisplayed("Imóveis"));
        assertTrue(allCategoriesPO.categoryIsDisplayed("Bebé e Criança"));
        assertTrue(allCategoriesPO.categoryIsDisplayed("Lazer"));
        assertTrue(allCategoriesPO.categoryIsDisplayed("Telemóveis, Tablets e Smartwatches"));
        assertTrue(allCategoriesPO.categoryIsDisplayed("Agricultura"));
        assertTrue(allCategoriesPO.categoryIsDisplayed("Animais"));
        assertTrue(allCategoriesPO.categoryIsDisplayed("Desporto"));
        assertTrue(allCategoriesPO.categoryIsDisplayed("Moda"));
        assertTrue(allCategoriesPO.categoryIsDisplayed("Móveis, Casa e Jardim"));
        assertTrue(allCategoriesPO.categoryIsDisplayed("Tecnologia"));
        assertTrue(allCategoriesPO.categoryIsDisplayed("Emprego"));
        assertTrue(allCategoriesPO.categoryIsDisplayed("Serviços"));
        assertTrue(allCategoriesPO.categoryIsDisplayed("Equipamentos e Ferramentas"));
        assertTrue(allCategoriesPO.categoryIsDisplayed("Outras Vendas"));
    }
}
