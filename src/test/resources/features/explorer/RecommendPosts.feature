Feature: Recommend Posts

  @RecommendPosts @E2E @Test_001 @Regression
  Scenario: Search for a video
    Given the app is open and ready to use
    When the user is on the Explore Page
    Then the app should reccomend posts for the user

  @RecommendPosts @E2E @Test_002 @Regression
  Scenario: Open a recommended post
    Given the app is open and ready to use
    And the user is on the Explore Page
    When the user selects a recommended post
    Then the post details page is displayed