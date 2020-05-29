
Feature: Edit Account
  As a Authorized Manager
  The user wants to edit a new existing customer account

  @edit_account
  Scenario: Edit existing customer account
    Given the user has been navigated to "Edit Account" page
    When the user inputs Account ID into Account No field
    And the user clicks on "Submit" button
    And the user selects "Current" in Edit Account type dropdown
    And the user clicks on "Submit" button
    Then the success message "Account details updated Successfully!!!" and Updated Account details should be displayed on page
    
