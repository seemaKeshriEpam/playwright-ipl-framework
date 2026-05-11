package Tests;

import Base.BaseTest;
import Page.IPLTablePage;
import org.testng.annotations.Test;

public class ScreenshotTest extends BaseTest {

    @Test
    public void verifyScreenshotAndScroll() {

        IPLTablePage iplTablePage =
                new IPLTablePage(page);

        // Scroll demonstration
        iplTablePage.scrollToBottom();

        System.out.println(
                "Scrolled to bottom successfully");

        // Screenshot capture
        iplTablePage.takeScreenshot(
                "ipl-scroll-page");

        System.out.println(
                "Screenshot captured successfully");
    }
}