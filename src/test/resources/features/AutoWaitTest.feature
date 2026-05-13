Feature: Verify Playwright Auto Wait Functionality

  Scenario: Verify top IPL team without explicit wait
    Given User launches the IPL table page
    When User scrolls to the bottom of the page
    And User fetches the top IPL team
    Then Top IPL team should be displayed
