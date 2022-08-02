package cucumber_step_defs;

import io.appium.java_client.MobileElement;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static cucumber_step_defs.Hooks.appiumDriver;

public class DemoApkStepDef {

    @When("user navigates to home page")
    public void navigateToHomePage() {
        MobileElement link = appiumDriver.findElementByAccessibilityId("Access'ibility");
        link.click();
    }

    @Then("user tries different scenarios")
    public void differentSelectorScenarios() {

    }
}
