package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import page.IPLTablePage;
import utils.PlaywrightFactory;

public class AutoWaitStepDef {

    private final IPLTablePage iplTablePage;
    String topTeam;

    public AutoWaitStepDef() {
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
}
