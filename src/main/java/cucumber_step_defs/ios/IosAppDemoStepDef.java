package cucumber_step_defs.ios;

import GeneralSetup.ViewsInitializerIos;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class IosAppDemoStepDef extends ViewsInitializerIos {

    @When("user is on home page$")
    public void userTerminatesApp() {
        homeViewIOS.verifyUserIsOnHomePage();
    }

    @Then("user checks different selectors strategies")
    public void userChecksDifferentSelectorsStrategies() {
        homeViewIOS.differentSelectorsStrategies();
    }
}
