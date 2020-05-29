
Feature: Add New Customer
  As a Registered Manager
  The user wants to add a new customer to the system

  @new_customer
  Scenario Outline: Add New Customer
    Given the user has been navigated to "New Customer" page
    When the user inputs data into required fields of Add New Customer Form
      | Customer Name   | Gender   | DateOfBirth   | Address   | City   | State   | Pin   | Mobile Number   | Email   | Password   | Success Message   |
      | <Customer Name> | <Gender> | <DateOfBirth> | <Address> | <City> | <State> | <Pin> | <Mobile Number> | <Email> | <Password> | <Success Message> |
    And the user clicks on "Submit" button
    Then the Registered Successfully message and Customer Info should be displayed
      | Customer Name   | Gender   | Birthdate     | Address   | City   | State   | Pin   | Mobile No.      | Email   | Success Message   |
      | <Customer Name> | <Gender> | <DateOfBirth> | <Address> | <City> | <State> | <Pin> | <Mobile Number> | <Email> | <Success Message> |

    Examples: New Customer info
      | Customer Name | Gender | DateOfBirth | Address         | City    | State   | Pin    | Mobile Number | Email     | Password  | Success Message                     |
      | Kevin Pham    | male   | 1989-02-15  | 123  ABC Street | Phoenix | Arizona | 880088 |    0987654321 | Kevinpham | abcxyz123 | Customer Registered Successfully!!! |
