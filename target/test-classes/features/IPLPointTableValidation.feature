Feature: IPL Points Table Validation

  Background:
    Given User launches the IPL table page
    When User scrolls to the bottom of the page

  Scenario: Verify Playwright Auto Wait Functionality
    And User fetches the top IPL team
    Then Top IPL team should be displayed

  Scenario: Verify IPL Table Data
    And User prints the IPL points table
    Then Total IPL teams should be greater than 0
    And Top IPL team points should be displayed
    And Team "MI" should be present in the table
    And User prints all IPL team names
    And User captures screenshot with name "ipl-points-table"

  Scenario: Verify Screenshot And Scroll Functionality
    Then Scroll should be successful
    And User captures screenshot with name "ipl-scroll-page"