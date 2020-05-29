
Feature: Logout System
  As a logged in user
  The user wants to log out the system

  @logout
  Scenario: Log out the system
    Given the user has been navigated to Manager home page
    When the user clicks on Log out link and accept alert
    Then the Log in page is displayed after the users accepts alert
