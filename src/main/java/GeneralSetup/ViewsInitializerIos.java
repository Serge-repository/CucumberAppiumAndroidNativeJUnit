package GeneralSetup;

import views.ios.HomeViewIOS;

import static cucumber_step_defs.TestBasis.appiumDriver;
import static cucumber_step_defs.TestBasis.wait;

public class ViewsInitializerIos {
    public final HomeViewIOS homeViewIOS = new HomeViewIOS(appiumDriver, wait);
}
