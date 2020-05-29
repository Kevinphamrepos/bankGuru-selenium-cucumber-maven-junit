
Feature: Edit Customer
  As a Authorized Manager
  The user wants to edit existing customer's information

  @edit_customer
  Scenario Outline: Edit Customer
    Given the user has been navigated to "Edit Customer" page
    When the user inputs data into Customer ID field
    And the user clicks on "Submit" button
    Then the Edit Customer Form should be displayed
    When the user inputs data into required fields of Edit Customer Form
      | Address   | City   | State   | Pin   | Mobile Number   | Email   | Success Message   |
      | <Address> | <City> | <State> | <Pin> | <Mobile Number> | <Email> | <Success Message> |
    And the user clicks on "Submit" button
    Then the Customer Details Updated Successfully message and Updated Customer Info should be displayed
      | Customer ID | Address   | City   | State   | Pin   | Mobile No.      | Email   | Success Message   |
      | customerID  | <Address> | <City> | <State> | <Pin> | <Mobile Number> | <Email> | <Success Message> |

    Examples: Edit Customer info
      | Address        | City        | State      | Pin    | Mobile Number | Email     | Success Message                          |
      | 789 XYZ Street | Los Angeles | California | 110011 |    0987654321 | Kevinpham | Customer details updated Successfully!!! |
