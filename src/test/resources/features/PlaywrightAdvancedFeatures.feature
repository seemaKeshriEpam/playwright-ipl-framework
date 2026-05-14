Feature: Playwright Advanced Features Demonstration

  Background:
    Given User launches the IPL website

  Scenario: Verify Cross Browser Execution
    When User runs the framework on browser
    Then IPL website should launch successfully

  Scenario: Verify Mobile Emulation Functionality
    When User launches IPL website in mobile view
    Then Mobile view should be displayed successfully

  Scenario: Verify Page Navigation Details
    When User gets current page title
    And User gets current page URL
    Then Page details should be displayed successfully

  Scenario: Verify Browser Information
    When User gets current browser details
    Then Browser information should be displayed successfully