package Tests;

import Base.BaseTest;
import com.microsoft.playwright.*;
import org.testng.annotations.*;
import Page.IPLTablePage;
import org.testng.Assert;

public class IPLTableTest extends BaseTest {

    @Test
    public void verifyIPLTableData() {
        IPLTablePage iplTablePage = new IPLTablePage(page);

        // Auto wait demonstration
        //iplTablePage.waitForPageLoad();

        // Scroll to table
        iplTablePage.scrollToBottom();

        // Print full table
        iplTablePage.printPointsTable();

        // Print total teams
        System.out.println(
                "Total Teams: "
                        + iplTablePage.getTotalTeams());

        // Print top team
        System.out.println(
                "Top Team: "
                        + iplTablePage.getTopTeam());

        // Print top team points
        System.out.println(
                "Top Team Points: "
                        + iplTablePage.getTopTeamPoints());

        // Validate team exists
        System.out.println(
                "Is MI Present: "
                        + iplTablePage.isTeamPresent("Mumbai"));
        Assert.assertTrue(
                iplTablePage.getTotalTeams() > 0,
                "No teams found in IPL table");

        // Validate top team is not null
        Assert.assertNotNull(
                iplTablePage.getTopTeam(),
                "Top team name is null");

        // Validate Specific Team Exists
        Assert.assertTrue(
                iplTablePage.isTeamPresent("MI"),
                "Mumbai team not found");

        // Print only team names
        iplTablePage.printTeamNames();

        // Screenshot for reporting/debugging
        iplTablePage.takeScreenshot("ipl-points-table");
    }

}