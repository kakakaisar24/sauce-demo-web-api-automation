@api
Feature: DummyAPI User Management

  Scenario: Get user by ID
    Given I have an existing user ID
    When I request the user by ID
    Then the API response status should be 200
    And the response should contain the user ID

  Scenario: Get user by ID that does not exist
    Given I have a non-existing user ID
    When I request the user by ID
    Then the API response status should be 404

  Scenario: Create, update, and delete a user
    Given I prepare a new user
    When I create the user
    Then the API response status should be 200
    And the response should contain the created user
    When I update the created user
    Then the API response status should be 200
    And the response should contain the updated first name
    When I delete the created user
    Then the API response status should be 200

  Scenario: Get list of tags
    When I request the list of tags
    Then the API response status should be 200
    And the response should contain a list of tags