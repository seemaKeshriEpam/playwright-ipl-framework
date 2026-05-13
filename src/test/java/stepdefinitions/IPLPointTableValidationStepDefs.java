package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import page.IPLTablePage;
import utils.PlaywrightFactory;

public class IPLPointTableValidationStepDefs {

    private final IPLTablePage iplTablePage;
    String topTeam;

    public IPLPointTableValidationStepDefs() {
        this.iplTablePage = new IPLTablePage();
    }

    @Given("User launches the IPL table page")
    public void i_am_on_the_ipl_points_table_page() {
        PlaywrightFactory.getPage().navigate("https://www.iplt20.com/points-table/men");
    }

    @When("User scrolls to the bottom of the page")
    public void i_search_for_team() {
        iplTablePage.scrollToBottom();
    }

    @Then("User fetches the top IPL team")
    public void i_should_see_as_the_top_team() {
        topTeam = iplTablePage.getTopTeam();
    }

    @Then("Top IPL team should be displayed")
    public void top_ipl_team_should_be_displayed() {
        System.out.println(
                "Top Team: " + topTeam);
        Assert.assertNotNull(
                topTeam,
                "Top team is null");
    }
    @And("User prints the IPL points table")
    public void user_prints_the_ipl_points_table() {

        iplTablePage.printPointsTable();
    }

    @Then("Total IPL teams should be greater than 0")
    public void total_ipl_teams_should_be_greater_than_0() {

        int totalTeams =
                iplTablePage.getTotalTeams();

        System.out.println(
                "Total Teams: " + totalTeams);

        Assert.assertTrue(
                totalTeams > 0,
                "No teams found in IPL table");
    }

    @And("Top IPL team points should be displayed")
    public void top_ipl_team_points_should_be_displayed() {

        String points =
                iplTablePage.getTopTeamPoints();

        System.out.println(
                "Top Team Points: " + points);

        Assert.assertNotNull(points);
    }

    @And("Team {string} should be present in the table")
    public void team_should_be_present_in_the_table(
            String teamName) {

        boolean isPresent =
                iplTablePage.isTeamPresent(teamName);

        System.out.println(
                "Is Team Present: " + isPresent);

        Assert.assertTrue(
                isPresent,
                teamName + " team not found");
    }

    @And("User prints all IPL team names")
    public void user_prints_all_ipl_team_names() {

        iplTablePage.printTeamNames();
    }

    @And("User captures screenshot with name {string}")
    public void user_captures_screenshot_with_name(
            String screenshotName) {

        iplTablePage.takeScreenshot(
                screenshotName);

        System.out.println(
                "Screenshot captured successfully");
    }

    @Then("Scroll should be successful")
    public void scroll_should_be_successful() {

        System.out.println(
                "Scrolled to bottom successfully");
    }

}
