package Base;

import com.microsoft.playwright.Page;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.PlaywrightFactory;

public class BaseTest {

    protected Page page;

    @BeforeMethod
    public void setup() {

        page = PlaywrightFactory.initBrowser("chromium");

        page.navigate("https://example-ipl-table.com");
    }

    @AfterMethod
    public void tearDown() {

        PlaywrightFactory.closeBrowser();
    }
}