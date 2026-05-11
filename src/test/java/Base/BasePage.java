package Base;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.nio.file.Paths;
import java.util.List;

public class BasePage {

    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    // BASIC ACTIONS

    public void click(String locator) {
        page.locator(locator).click();
    }

    public void type(String locator, String text) {
        page.locator(locator).fill(text);
    }

    public void enterText(String locator, String text) {
        page.locator(locator).type(text);
    }

    public String getText(String locator) {
        return page.locator(locator).textContent();
    }

    public boolean isVisible(String locator) {
        return page.locator(locator).isVisible();
    }

    public boolean isEnabled(String locator) {
        return page.locator(locator).isEnabled();
    }

    public void clear(String locator) {
        page.locator(locator).clear();
    }

    // WAIT METHODS

//    public void waitForElement(String locator) {
//        page.locator(locator)
//                .waitFor(new Locator.WaitForOptions()
//                        .setState(WaitForSelectorState.VISIBLE));
//    }
//
//    public void waitForPageLoad() {
//        page.waitForLoadState();
//    }
//
//    public void waitForTimeout(int milliseconds) {
//        page.waitForTimeout(milliseconds);
//    }

    // DROPDOWN METHODS

    public void selectDropdownByValue(String locator, String value) {
        page.locator(locator).selectOption(value);
    }

    public void selectDropdownByLabel(String locator, String label) {
        page.locator(locator).selectOption(new SelectOption().setLabel(label));
    }

    // TABLE METHODS

    public int getRowCount(String rowsLocator) {
        return page.locator(rowsLocator).count();
    }

    public int getColumnCount(String columnsLocator) {
        return page.locator(columnsLocator).count();
    }

    public String getCellValue(String rowLocator, int rowIndex, int columnIndex) {

        Locator row = page.locator(rowLocator).nth(rowIndex);

        return row.locator("td").nth(columnIndex).textContent();
    }

    public void printTableData(String rowLocator) {

        Locator rows = page.locator(rowLocator);

        int rowCount = rows.count();

        for (int i = 0; i < rowCount; i++) {

            List<String> rowData =
                    rows.nth(i)
                            .locator("td")
                            .allTextContents();

            System.out.println(rowData);
        }
    }

    // SCROLL METHODS

    public void scrollIntoView(String locator) {
        page.locator(locator).scrollIntoViewIfNeeded();
    }

    public void scrollToBottom() {
        page.evaluate("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollToTop() {
        page.evaluate("window.scrollTo(0, 0)");
    }

    // SCREENSHOT METHODS

    public void takeScreenshot(String fileName) {

        page.screenshot(
                new Page.ScreenshotOptions()
                        .setPath(Paths.get("screenshots/" + fileName + ".png"))
                        .setFullPage(true));
    }

    // NAVIGATION METHODS

    public void navigateTo(String url) {
        page.navigate(url);
    }

    public String getPageTitle() {
        return page.title();
    }

    public String getPageURL() {
        return page.url();
    }

    // ALERT HANDLING

    public void acceptAlert() {

        page.onDialog(dialog -> {
            dialog.accept();
        });
    }

    public void dismissAlert() {

        page.onDialog(dialog -> {
            dialog.dismiss();
        });
    }

    // ASSERTION HELPERS

    public boolean verifyText(String locator, String expectedText) {

        String actualText = getText(locator);

        return actualText.trim().equals(expectedText);
    }
}