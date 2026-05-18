package stepdefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import page.IPLTablePage;
import utils.PlaywrightFactory;

@Log4j2
public class IPLPointTableValidationStepDefs {

    private final IPLTablePage iplTablePage;
    String topTeam;

    public IPLPointTableValidationStepDefs() {
        this.iplTablePage = new IPLTablePage();
    }

    @Given("User launches the IPL table page")
    public void i_am_on_the_ipl_points_table_page() {
        try{
            PlaywrightFactory.getPage().navigate("https://www.iplt20.com/points-table/men");
            log.info("Navigated  to IPL points table page successfully");
        }
        catch (Exception e){
            log.error("Failed to navigate to IPL points table page: " + e.getMessage());
            throw e;
        }
    }

    @When("User scrolls to the bottom of the page")
    public void i_search_for_team() {
        try{
            iplTablePage.scrollToBottom();
            log.info("Scrolled to bottom successfully");
        }
        catch (Exception e){
            log.error("Failed to scroll to bottom: " + e.getMessage());
            throw e;
        }
    }

    @Then("User fetches the top IPL team")
    public void i_should_see_as_the_top_team() {
        try{
            topTeam = iplTablePage.getTopTeam();
            log.info("Fetched top IPL team successfully: " + topTeam);
        }
        catch (Exception e){
            log.error("Failed to fetch top IPL team: " + e.getMessage());
            throw e;
        }
    }

    @Then("Top IPL team should be displayed")
    public void top_ipl_team_should_be_displayed() {
        try {
            Assert.assertFalse(
                    topTeam.isEmpty(),
                    "Top team is empty");
            log.info("Top IPL team is displayed successfully: " + topTeam);
        } catch (AssertionError e) {
            log.error("Top IPL team validation failed: " + e.getMessage());
            throw e;
        }
    }
    @And("User prints the IPL points table")
    public void user_prints_the_ipl_points_table() {
        try {
            iplTablePage.printPointsTable();
            log.info("Printed IPL points table successfully");
        } catch (Exception e) {
            log.error("Failed to print IPL points table: " + e.getMessage());
            throw e;
        }
    }

    @Then("Total IPL teams should be greater than 0")
    public void total_ipl_teams_should_be_greater_than_0() {
        try {
            int totalTeams = iplTablePage.getTotalTeams();
            Assert.assertTrue(
                    totalTeams > 0,
                    "Total teams should be greater than 0");
            log.info("Total IPL teams validation successful: " + totalTeams);
        } catch (AssertionError e) {
            log.error("Total IPL teams validation failed: " + e.getMessage());
            throw e;
        }
    }

    @And("Top IPL team points should be displayed")
    public void top_ipl_team_points_should_be_displayed() {
        try {
            String topTeamPoints = iplTablePage.getTopTeamPoints();
            Assert.assertFalse(
                    topTeamPoints.isEmpty(),
                    "Top team points is empty");
            log.info("Top IPL team points are displayed successfully: " + topTeamPoints);
        } catch (AssertionError e) {
            log.error("Top IPL team points validation failed: " + e.getMessage());
            throw e;
        }
    }

    @And("Team {string} should be present in the table")
    public void team_should_be_present_in_the_table(String teamName) {
        try {
            boolean isPresent = iplTablePage.isTeamPresent(teamName);
            Assert.assertTrue(
                    isPresent,
                    "Team '" + teamName + "' should be present in the table");
            log.info("Team '" + teamName + "' is present in the table");
        } catch (AssertionError e) {
            log.error("Team presence validation failed for '" + teamName + "': " + e.getMessage());
            throw e;
        }
    }

    @And("User prints all IPL team names")
    public void user_prints_all_ipl_team_names() {
        try {
            iplTablePage.printTeamNames();
            log.info("Printed all IPL team names successfully");
        } catch (Exception e) {
            log.error("Failed to print all IPL team names: " + e.getMessage());
            throw e;
        }
    }

    @And("User captures screenshot with name {string}")
    public void user_captures_screenshot_with_name(String screenshotName) {
        try {
            iplTablePage.takeScreenshot(screenshotName);
            log.info("Screenshot captured successfully: " + screenshotName);
        } catch (Exception e) {
            log.error("Failed to capture screenshot: " + e.getMessage());
            throw e;
        }
    }

    @Then("Scroll should be successful")
    public void scrollShouldBeSuccessful() {
        try {
            boolean isScrolled = iplTablePage.isScrolledToBottom();
            Assert.assertTrue(
                    isScrolled,
                    "Scroll to bottom should be successful");
            log.info("Scroll to bottom validation successful");
        } catch (AssertionError e) {
            log.error("Scroll to bottom validation failed: " + e.getMessage());
            throw e;
        }
    }
}
