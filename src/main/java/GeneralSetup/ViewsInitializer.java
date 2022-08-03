package GeneralSetup;

import views.HomeView;
import views.TextFieldsView;
import views.ViewsView;

import static cucumber_step_defs.TestBasis.*;

public class ViewsInitializer {
    public final HomeView homeView = new HomeView(appiumDriver, wait);
    public final ViewsView viewsView = new ViewsView(appiumDriver, wait);
    public final TextFieldsView textFieldsView = new TextFieldsView(appiumDriver, wait);
}
