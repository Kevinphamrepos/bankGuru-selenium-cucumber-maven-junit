
Feature: Add New Account
  As a Authorized Manager
  The user wants to add a new account to existing customer

  @new_account
  Scenario: Add New Account to existing customer
    Given the user has been navigated to "New Account" page
    When the user inputs Customer ID into Customer ID field
    And the user selects "Savings" in New Account type dropdown
    And the user inputs "5000" into Initial deposit field
    And the user clicks on "submit" button
    Then the success message "Account Generated Successfully!!!" and Created Account details should be displayed on page
    
