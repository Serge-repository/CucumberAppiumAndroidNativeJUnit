package GeneralSetup;

import views.android.HomeView;
import views.android.TextFieldsView;
import views.android.ViewsView;

import static cucumber_step_defs.TestBasis.*;

public class ViewsInitializerAndroid {
    public final HomeView homeView = new HomeView(appiumDriver, wait);
    public final ViewsView viewsView = new ViewsView(appiumDriver, wait);
    public final TextFieldsView textFieldsView = new TextFieldsView(appiumDriver, wait);
}
