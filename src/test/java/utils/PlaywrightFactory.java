package utils;

import com.microsoft.playwright.*;

public class PlaywrightFactory {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext browserContext;
    private static Page page;

    public static Page initBrowser(String browserName) {

        playwright = Playwright.create();

        switch (browserName.toLowerCase()) {

            case "chromium":

                browser = playwright.chromium()
                        .launch(new BrowserType.LaunchOptions()
                                .setHeadless(false));

                break;

            case "firefox":

                browser = playwright.firefox()
                        .launch(new BrowserType.LaunchOptions()
                                .setHeadless(false));

                break;

            case "webkit":

                browser = playwright.webkit()
                        .launch(new BrowserType.LaunchOptions()
                                .setHeadless(false));

                break;

            default:
                throw new RuntimeException("Please pass correct browser value");
        }

        browserContext = browser.newContext();

        page = browserContext.newPage();

        page.setDefaultTimeout(30000);

        return page;
    }

    public static Page getPage() {
        return page;
    }

    public static void closeBrowser() {

        if (page != null) {
            page.close();
        }

        if (browserContext != null) {
            browserContext.close();
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }
}