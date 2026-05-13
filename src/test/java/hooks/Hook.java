package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.PlaywrightFactory;
import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;

public class Hook {
    @Before
    public void setUp(){
        String browser = System.getProperty("browser","chromium");
        PlaywrightFactory.initBrowser(browser);
    }
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            Page page = PlaywrightFactory.getPage();
            if (page!=null){
                byte[] screenshot = page.screenshot(
                        new Page.ScreenshotOptions()
                                .setFullPage(true)
                );
                Allure.addAttachment(
                        "Failed Screenshot",
                        "image/png",
                        new ByteArrayInputStream(screenshot),
                        ".png"
                );
            }
        }
        PlaywrightFactory.closeBrowser();
    }
}
