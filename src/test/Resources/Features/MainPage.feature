Feature: Main Page Verification

  Scenario: Verify Main Page Url
    Given user is on the main page
    When the main page URL should be correct
    When go to tablet category
    When the tablet page URL should be correct
