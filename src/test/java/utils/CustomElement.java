package utils;

import io.appium.java_client.MobileElement;

import static cucumber_step_defs.Hooks.appiumDriver;

public class CustomElement {

    public static MobileElement byAccessibilityId(String locator) {
        return appiumDriver.findElementByAccessibilityId(locator);
    }
}
