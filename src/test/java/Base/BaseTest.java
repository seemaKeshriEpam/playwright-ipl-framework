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

        page.navigate("https://www.iplt20.com/points-table/men");
    }

    @AfterMethod
    public void tearDown() {

        PlaywrightFactory.closeBrowser();
    }
}