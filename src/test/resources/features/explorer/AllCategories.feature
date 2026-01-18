Feature: All Categories filter

  Background:
    Given the app is open and ready to use
    And the user is on the Explore Page

  @RecommendPosts @E2E @Test_003 @Regression
  Scenario: All correct categories are being displayed
    When the user clicks on All Categories button
    Then all available categories are displayed to the user

  @RecommendPosts @E2E @Test_004 @Regression
  Scenario Outline: Access categories
    When the user clicks on All Categories button
    And the user clicks on the category "<Category>" on All Categories Page
    And the user clicks on all the categories option on SubCategories Page
    Then all posts related to the selected category are displayed
    Examples:
      | Category                           |
      | Carros, motos e barcos             |
      | Imóveis                            |
