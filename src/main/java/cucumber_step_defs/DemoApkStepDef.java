package cucumber_step_defs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import views.HomeView;
import views.TextFieldsView;
import views.ViewsView;

import static cucumber_step_defs.TestBasis.appiumDriver;
import static cucumber_step_defs.TestBasis.wait;

public class DemoApkStepDef {

    private final HomeView homeView = new HomeView(appiumDriver, wait);
    private final ViewsView viewsView = new ViewsView(appiumDriver, wait);
    private final TextFieldsView textFieldsView = new TextFieldsView(appiumDriver, wait);

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
}
