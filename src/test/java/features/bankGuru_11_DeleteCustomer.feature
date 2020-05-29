
Feature: Delete Customer
  As a Authorized Manager
  The user wants to delete a existing customer

  @delete_customer
  Scenario: Delete a existing customer
    Given the user has been navigated to "Delete Customer" page
    When the user inputs Customer ID into Customer ID field of Delete Customer Form
    And the user clicks on "Submit" button
    And the user accepts a confirmation and successful alert
    Then the Manager home page should be displayed after the users accepts alert
    And that Customer ID should not exist
    
    
