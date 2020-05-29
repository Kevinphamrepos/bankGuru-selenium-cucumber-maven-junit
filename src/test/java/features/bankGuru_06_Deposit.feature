
Feature: Deposit to Account
  As a Authorized Manager
  The user wants to deposit a amount to a existing customer account

  @deposit
  Scenario Outline: Deposit a amount to a existing customer account
    Given the user has been navigated to "Deposit" page
    When the user inputs data into required fields of Amount Deposit Form
      | Account No   | Amount   | Description   |
      | <Account No> | <Amount> | <Description> |
    And the user clicks on "Submit" button
    Then the success message and Transaction details of Deposit should be displayed on page
      | Account No   | Amount Credited | Description   | Type of Transaction | Success Message                | Current Balance |
      | <Account No> | <Amount>        | <Description> | Deposit             | Transaction details of Deposit | Balance         |

    Examples: Deposit Information
      | Account No    | Amount | Description |
      | accountNumber |  10000 | Deposit     |
