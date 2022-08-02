package GeneralSetup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ActionsWithDeviceAndApp;
import utils.TouchActionClass;
import utils.WaitUtils;

public abstract class GeneralView {
    public AppiumDriver<MobileElement> appiumDriver;
    public WebDriverWait wait;

    public final WaitUtils waitUtils = new WaitUtils();
    public final TouchActionClass touchAction = new TouchActionClass();
    public final ActionsWithDeviceAndApp actionsWithDeviceAndApp = new ActionsWithDeviceAndApp();

    public GeneralView(AppiumDriver<MobileElement> appiumDriver, WebDriverWait wait) {
        this.appiumDriver = appiumDriver;
        this.wait = wait;
        PageFactory.initElements(new AppiumFieldDecorator(this.appiumDriver), this);
    }

    public WaitUtils getWaitUtils() {
        return waitUtils;
    }

    public TouchActionClass getTouchAction() {
        return touchAction;
    }

    public ActionsWithDeviceAndApp getActionsWithDeviceAndApp() {
        return actionsWithDeviceAndApp;
    }

    public void hideKeyboard(){
        appiumDriver.hideKeyboard();
    }

    public void enterKeyUsingKeyboard(AndroidKey... androidKey){
        for (AndroidKey key : androidKey) {
            ((AndroidDriver) appiumDriver).pressKey(new KeyEvent().withKey(key));
        }
    }

    public void changeAppContext(String contextName){
        appiumDriver.context(contextName);
    }

    public String getElementAttribute(MobileElement element, String attributeName){
        return element.getAttribute(attributeName);
    }
}