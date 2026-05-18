package stepdefinitions;

import page.IPLTablePage;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.*;
import utils.PlaywrightFactory;

public class PlaywrightAdvancedFeaturesStepDef {

    Page page = PlaywrightFactory.getPage();

    @When("User runs the framework on browser")
    public void user_runs_the_framework_on_browser() {

        String browserName =
                page.context().browser().browserType().name();

        System.out.println("Running on browser: " + browserName);
    }

    @Then("IPL website should launch successfully")
    public void ipl_website_should_launch_successfully() {

        String title = page.title();

        System.out.println("Page Title: " + title);
    }

    //iPhone-like dimensions
    @When("User launches IPL website in mobile view")
    public void user_launches_ipl_website_in_mobile_view() {

        page.setViewportSize(390, 844);

        page.reload();

        System.out.println("Mobile view launched successfully");
    }

    @Then("Mobile view should be displayed successfully")
    public void mobile_view_should_be_displayed_successfully() {

        int width = page.viewportSize().width;
        int height = page.viewportSize().height;

        System.out.println("Mobile Width: " + width);
        System.out.println("Mobile Height: " + height);
    }
    @When("User gets current page title")
    public void user_gets_current_page_title() {

        String title = page.title();

        System.out.println("Current Page Title: " + title);
    }

    @When("User gets current page URL")
    public void user_gets_current_page_url() {

        String url = page.url();

        System.out.println("Current Page URL: " + url);
    }

    @Then("Page details should be displayed successfully")
    public void page_details_should_be_displayed_successfully() {

        System.out.println("Page details fetched successfully");
    }
    @When("User gets current browser details")
    public void user_gets_current_browser_details() {

        String browserName =
                page.context().browser().browserType().name();

        System.out.println("Current Browser: " + browserName);
    }

    @Then("Browser information should be displayed successfully")
    public void browser_information_should_be_displayed_successfully() {

        System.out.println("Browser information fetched successfully");
    }

}