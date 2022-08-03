@Smoke
Feature: Demo

  Scenario: Login as a authenticated user
    When user navigates to views page
    Then user tries scrolls and text input

  Scenario: Terminate and install app
    Given user terminates app
    When user activates and terminates app again
    Then user is able install app again