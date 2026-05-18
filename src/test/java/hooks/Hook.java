package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.PlaywrightFactory;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hook {
    @Before
    public void setUp(Scenario scenario){
        String browser = System.getProperty("browser","chromium");
        PlaywrightFactory.initBrowser(browser);

        BrowserContext context = PlaywrightFactory.getBrowserContext();
        if (context != null) {
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));
        }
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

        stopAndSaveTrace(scenario);
        PlaywrightFactory.closeBrowser();
    }

    private void stopAndSaveTrace(Scenario scenario) {
        BrowserContext context = PlaywrightFactory.getBrowserContext();
        if (context == null) {
            return;
        }

        try {
            Path traceDir = Paths.get("target/traces");
            Files.createDirectories(traceDir);

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            String status = scenario.isFailed() ? "FAIL" : "PASS";
            String scenarioName = sanitizeScenarioName(scenario.getName());
            Path tracePath = traceDir.resolve(scenarioName + "-" + status + "-" + timestamp + ".zip");

            context.tracing().stop(new Tracing.StopOptions().setPath(tracePath));
            Allure.addAttachment("Playwright Trace - " + scenario.getName(), "application/zip",
                    new ByteArrayInputStream(Files.readAllBytes(tracePath)), ".zip");
        } catch (IOException e) {
            throw new RuntimeException("Unable to save Playwright trace", e);
        }
    }

    private String sanitizeScenarioName(String scenarioName) {
        return scenarioName.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}
