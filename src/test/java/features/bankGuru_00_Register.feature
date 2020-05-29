
Feature: Generate Access
  As a manager
  The user wants to generate access to System

  @register
  Scenario: Generating Access
    Given the user has been navigated to Register page
    When the user inputs email into Email ID field
    And the user clicks on "Submit" button
    Then the Access details should be displayed on page
