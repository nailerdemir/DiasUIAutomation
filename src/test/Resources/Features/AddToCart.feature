Feature: Add To Cart Verification

  Scenario: Add Product To Cart And Verify
    Given the user is on the main page
    And the main page URL is verified to be correct
    When the user navigates to the tablet category
    And the tablet category page URL is verified to be correct
    And the user filters the products by 'Apple' brand and '13.2 inch' screen size
    And the user selects the highest priced tablet
    And the user clicks the 'Add to Cart' button
    When the user is redirected to the cart page
    Then the product should be successfully added to the cart
    And the product price on the cart page should match the price on the product detail page

