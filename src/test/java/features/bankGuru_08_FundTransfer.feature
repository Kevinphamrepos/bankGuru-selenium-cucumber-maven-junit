
Feature: Fund Transfer
	As a Authorized Manager
  The user wants to transfer a amount from existing payers account to existingpayees account

  @fund_transfer
  Scenario: Fund Transfer from existing payers account to existing payees account
    Given the user has been navigated to "Fund Transfer" page
    When the user inputs data into required fields of Fund Transfer Form
    And the user clicks on "Submit" button
    Then the success message and Fund Transfer Details should be displayed on page
