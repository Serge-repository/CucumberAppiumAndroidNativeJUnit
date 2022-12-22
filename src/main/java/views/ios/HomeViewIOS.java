package views.ios;

import GeneralSetup.DataManagement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.MyElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class HomeViewIOS extends DataManagement {

    @iOSXCUITFindBy(accessibility = "Activity Indicators")
    private MobileElement activityIndicatorsButton;
    // "type" field in the appium inspector
    @iOSXCUITFindBy(className = "XCUIElementTypeStaticText")
    private List<MobileElement> classNameElement;

//    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Accessibility\")")
//    @iOSXCUITFindBy(accessibility = "Accessibility")
//    @FindBy(css = "abc")
//    private MobileElement elementForBothAndroidAndIOS;

    private final MyElement activityIndicators = new MyElement("Activity Indicators tab", MobileBy.AccessibilityId("Activity Indicators"));
    // "name" field in the appium inspector
    private final MyElement slidersNameElement = new MyElement("Sliders tab", MobileBy.name("Sliders"));
    // "id" field must be same as for "name" in appium inspector
    private final MyElement slidersIdElement = new MyElement("Sliders tab by id", MobileBy.id("Sliders"));
    // by ios ns predicate ("ios predicate string" in appium inspector
    private final MyElement predicate = new MyElement("Predicate", MobileBy.iOSNsPredicateString("label == \"Text View\""));
    // you can use OR here as well
    private final MyElement anotherPredicate = new MyElement("Another Predicate", MobileBy.iOSNsPredicateString("type == \"XCUIElementTypeStaticText\" AND label == \"Text View\""));

    public HomeViewIOS(AppiumDriver<MobileElement> appiumDriver, WebDriverWait wait) {
        super(appiumDriver, wait);
    }

    public void verifyUserIsOnHomePage() {
        wait.until(ExpectedConditions.visibilityOf(activityIndicatorsButton));
        assertTrue("Element is not visible", appiumDriver.findElementByAccessibilityId("Activity Indicators").isDisplayed());
    }

    public void differentSelectorsStrategies() {
        System.out.println(appiumDriver.findElementByAccessibilityId("Activity Indicators").getText());
        System.out.println(activityIndicators.getText());
        System.out.println(activityIndicatorsButton.getText());

        System.out.println(slidersNameElement.getText());
        System.out.println(classNameElement.get(1).getText());
        System.out.println(slidersIdElement.getText());
        System.out.println(appiumDriver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Text View\"]").getText());
        System.out.println(predicate.getText());
        System.out.println(anotherPredicate.getText());
    }

}
