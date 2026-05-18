package utils;

import com.microsoft.playwright.*;

public class PlaywrightFactory {

    private static final ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
    private static final ThreadLocal<Page> tlPage = new ThreadLocal<>();

    public static Page initBrowser(String browserName) {

        Playwright playwright = Playwright.create();
        tlPlaywright.set(playwright);

        // Use headless mode in CI environments or when explicitly set
        boolean isCI = System.getenv("CI") != null;
        boolean headless = isCI || Boolean.parseBoolean(System.getProperty("headless", "false"));

        Browser browser;
        switch (browserName.toLowerCase()) {

            case "chromium":
                browser = playwright.chromium()
                        .launch(new BrowserType.LaunchOptions()
                                .setHeadless(headless));
                break;

            case "firefox":
                browser = playwright.firefox()
                        .launch(new BrowserType.LaunchOptions()
                                .setHeadless(headless));
                break;

            case "webkit":
                browser = playwright.webkit()
                        .launch(new BrowserType.LaunchOptions()
                                .setHeadless(headless));
                break;

            default:
                throw new RuntimeException("Please pass correct browser value");
        }

        tlBrowser.set(browser);

        BrowserContext browserContext = browser.newContext();
        tlBrowserContext.set(browserContext);

        Page page = browserContext.newPage();
        tlPage.set(page);

        page.setDefaultTimeout(30000);

        return page;
    }

    public static Page getPage() {
        return tlPage.get();
    }

    public static BrowserContext getBrowserContext() {
        return tlBrowserContext.get();
    }

    public static void closeBrowser() {

        Page page = tlPage.get();
        if (page != null) {
            page.close();
            tlPage.remove();
        }

        BrowserContext browserContext = tlBrowserContext.get();
        if (browserContext != null) {
            browserContext.close();
            tlBrowserContext.remove();
        }

        Browser browser = tlBrowser.get();
        if (browser != null) {
            browser.close();
            tlBrowser.remove();
        }

        Playwright playwright = tlPlaywright.get();
        if (playwright != null) {
            playwright.close();
            tlPlaywright.remove();
        }
    }
}