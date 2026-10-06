@web
@e2e
Feature: SauceDemo End to End Purchase

  @e2e-purchase
  Scenario: User completes a purchase successfully
    Given user logs in with valid credentials
    When user adds Sauce Labs Backpack to cart
    And user opens the shopping cart
    Then shopping cart should contain "Sauce Labs Backpack"
    When user proceeds to checkout
    And user enters checkout information
    And user completes the order
    Then order confirmation should be displayed