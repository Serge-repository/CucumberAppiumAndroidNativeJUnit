package cucumber_step_defs;

import io.appium.java_client.MobileElement;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import views.HomeView;

import static cucumber_step_defs.Hooks.appiumDriver;
import static cucumber_step_defs.Hooks.wait;

public class DemoApkStepDef {

    private final HomeView homeView = new HomeView(appiumDriver, wait);

    @When("user navigates to home page")
    public void navigateToHomePage() {
        MobileElement link = appiumDriver.findElementByAccessibilityId("Access'ibility");
        link.click();
    }

    @Then("user tries different scenarios")
    public void differentSelectorScenarios() {
        homeView.differentSelectorsStrategies();
    }
}
