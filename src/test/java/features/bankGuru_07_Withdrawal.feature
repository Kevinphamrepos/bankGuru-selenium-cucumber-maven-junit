
Feature: Withdrawal from Account
	As a Authorized Manager
  The user wants to withdrawal a amount from a existing customer account

  @withdrawal
  Scenario Outline: Withdrawal a amount from a existing customer account
    Given the user has been navigated to "Withdrawal" page
    When the user inputs data into required fields of Amount Withdrawal Form
      | Account No   | Amount   | Description   |
      | <Account No> | <Amount> | <Description> |
    And the user clicks on "Submit" button
    Then the success message and Transaction details of Withdrawal should be displayed on page
      | Account No   | Amount Debited | Description   | Type of Transaction | Success Message                | Current Balance |
      | <Account No> | <Amount>        | <Description> | Withdrawal             | Transaction details of Withdrawal | Balance  |

    Examples: Withdrawal Information
      | Account No    | Amount | Description |
      | accountNumber |  3000 | Withdrawal     |
    