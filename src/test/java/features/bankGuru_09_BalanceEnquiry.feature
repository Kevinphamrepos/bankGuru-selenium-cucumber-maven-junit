
Feature: Balance Enquiry
	As a Authorized Manager
  The user wants to enquiry balance of a existing customer account

  @balance_enquiry
  Scenario: Enquiry balance of a existing customer account
    Given the user has been navigated to "Balance Enquiry" page
    When the user inputs Account ID into Account No field of Balance Enquiry Form
   	And the user clicks on "Submit" button
    Then the Balance Details for that Account should be displayed on page
    
