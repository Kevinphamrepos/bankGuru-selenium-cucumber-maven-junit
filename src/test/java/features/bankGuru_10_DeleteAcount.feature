
Feature: Delete Account
  As a Authorized Manager
  The user wants to delete a existing customer account

  @delete_account
  Scenario: Delete a existing customer account
    Given the user has been navigated to "Delete Account" page
    When the user inputs Account ID into Account No field of Delete Account Form
    And the user clicks on "Submit" button
    And the user accepts a confirmation and successful alert
    Then the Manager home page should be displayed after the users accepts alert
    And that Account ID should not exist
