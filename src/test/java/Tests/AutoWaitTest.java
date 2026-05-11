package Tests;

import Base.BaseTest;
import Page.IPLTablePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AutoWaitTest extends BaseTest {

        @Test
        public void verifyAutoWaitFunctionality() {

            IPLTablePage iplTablePage =
                    new IPLTablePage(page);

            // No explicit wait used here

            // Scroll to dynamic table
            iplTablePage.scrollToBottom();

            // Fetch team directly
            String topTeam =
                    iplTablePage.getTopTeam();

            System.out.println(
                    "Top Team: " + topTeam);

            // Assertion
            Assert.assertNotNull(
                    topTeam,
                    "Top team is null");
        }
}

