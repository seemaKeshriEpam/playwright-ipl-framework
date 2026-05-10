package Tests;

import com.microsoft.playwright.*;
import org.testng.annotations.*;
import Page.IPLTablePage;

public class IPLTableTest {

    Playwright playwright;
    Browser browser;
    Page page;

    IPLTablePage iplTablePage;

    @BeforeMethod
    public void setup() {

        playwright = Playwright.create();

        browser = playwright.chromium()
                .launch(new BrowserType.LaunchOptions()
                        .setHeadless(false));

        page = browser.newPage();

        iplTablePage = new IPLTablePage(page);

        iplTablePage.navigateTo("https://example-ipl-table.com");
    }

    @Test
    public void verifyIPLTableData() {

        iplTablePage.waitForPageLoad();

        iplTablePage.printPointsTable();

        System.out.println(
                "Total Teams: "
                        + iplTablePage.getTotalTeams());
    }

    @AfterMethod
    public void tearDown() {

        page.close();
        browser.close();
        playwright.close();
    }
}