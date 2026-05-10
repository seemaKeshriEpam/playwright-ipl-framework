package Page;

import Base.BasePage;
import com.microsoft.playwright.Page;

public class IPLTablePage extends BasePage {

    public IPLTablePage(Page page) {
        super(page);
    }

    // Locators
    private final String tableRows = "//table/tbody/tr";
    private final String searchBox = "//input[@type='search']";

    public void searchTeam(String teamName) {
        type(searchBox, teamName);
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
}