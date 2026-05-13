package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.PlaywrightFactory;

public class Hook {
    @Before
    public void setUp(){
        String browser = System.getProperty("browser","chromium");
        PlaywrightFactory.initBrowser(browser);
    }
    @After
    public void tearDown(){
        PlaywrightFactory.closeBrowser();
    }
}
