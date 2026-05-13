package page;

import base.BasePage;
import com.microsoft.playwright.Page;

import java.util.List;

public class IPLTablePage extends BasePage {

    public IPLTablePage() {
        super();
    }

    // Locators
    // Table Rows
    private final String tableRows =
            "//table[contains(@class,'ih-td-tab')]//tbody/tr";

    // Team Name Column
    private final String teamNames =
            "//table[contains(@class,'ih-td-tab')]//tbody/tr//h2";

    // Points Column
    private final String pointsColumn =
            "//table[contains(@class,'ih-td-tab')]//tbody/tr/td[10]";

    private final String searchBox = "//input[@type='search']";

    public void searchTeam(String teamName) {
        type(searchBox, teamName);
    }

    public String getTopTeam() {

        return page.locator(teamNames)
                .first()
                .textContent();
    }

    public String getTopTeamPoints() {

        return page.locator(pointsColumn)
                .first()
                .textContent();
    }

    public int getTotalTeams() {
        return getRowCount(tableRows);
    }

    public String getTeamName(int rowIndex) {
        return getCellValue(tableRows, rowIndex, 0);
    }

    public void printPointsTable() {
        printTableData(tableRows);
    }

    public boolean isTeamPresent(String teamName) {

        return page.locator(teamNames)
                .allTextContents()
                .toString()
                .contains(teamName);
    }
    public void printTeamNames() {

        List<String> teams =
                page.locator(teamNames)
                        .allTextContents();

        for (String team : teams) {

            System.out.println(team);
        }
    }
}