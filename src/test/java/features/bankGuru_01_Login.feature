
Feature: Log in System
  As a registered Manager
  The user wants to log in System

  @login
  Scenario: Log in System
    Given the user has been navigated to Login Page
    When the user inputs userID and password into required fields
    And the user clicks on Login button
    Then the Welcome message should be displayed on Manager home page
