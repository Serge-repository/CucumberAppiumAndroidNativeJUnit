package cucumber_step_defs.android;

import GeneralSetup.ViewsInitializerAndroid;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.Duration;

import static cucumber_step_defs.TestBasis.appPackage;

public class AndroidApkDemoStepDef extends ViewsInitializerAndroid {

    @Given("user terminates app")
    public void userTerminatesApp() {
        viewsView.getActionsWithDeviceAndApp().terminateApp(Duration.ofMillis(2000));
    }

    @When("user activates and terminates app again")
    public void userActivatesAndTerminatesAppAgain() {
        viewsView.getActionsWithDeviceAndApp().activateApp("com.android.settings");
        viewsView.getActionsWithDeviceAndApp().terminateApp(Duration.ofMillis(2000));
    }

    @When("user navigates to views page")
    public void navigateToViewsPage() {
        homeView.selectViewsOption();
    }

    @Then("user tries scrolls and text input")
    public void scrollsAndTextInput() {
        viewsView.getTouchAction().scrollAction(448, 1752, 551, 389);
        viewsView.getTouchAction().scrollAction(448, 1752, 551, 389);
        viewsView.getTouchAction().scrollAction(448, 1752, 551, 389);
        viewsView.selectTextFieldsOption();
        textFieldsView.enterTextIntoHintField();
    }

    @Then("user is able install app again")
    public void userIsAbleInstallAppAgain() {
        viewsView.getActionsWithDeviceAndApp().activateApp(appPackage);
    }
}
