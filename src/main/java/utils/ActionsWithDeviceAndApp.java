package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.appmanagement.AndroidInstallApplicationOptions;
import io.appium.java_client.android.appmanagement.AndroidTerminateApplicationOptions;

import java.time.Duration;

import static cucumber_step_defs.TestBasis.*;

public class ActionsWithDeviceAndApp {
    public void terminateApp(Duration duration){
        appiumDriver.terminateApp(appPackage, new AndroidTerminateApplicationOptions()
                .withTimeout(Duration.ofMillis(duration.toMillis()))); // time to wait until app terminated
    }

    public void installApp(){
        appiumDriver.installApp(appPath, new AndroidInstallApplicationOptions()
                .withReplaceEnabled()); // allows to update apps with new versions
    }

//    public void checkIfAppInstalled() {
//        assertEquals(appiumDriver.isAppInstalled(appPackage));
//    }

    public void runAppInBackground() {
        appiumDriver.runAppInBackground(Duration.ofMillis(4000));
    }

    public void activateApp(String appPackage) {
        appiumDriver.activateApp(appPackage); // switch between apps
    }

    public void lockDevice(Integer seconds) {
        // lock device for some time, then unlock
        ((AndroidDriver) appiumDriver).lockDevice(Duration.ofSeconds(seconds)); // only available in AndroidDriver class
    }

    public void lockDeviceForever() {
        // lock device forever
        ((AndroidDriver) appiumDriver).lockDevice(); // only available in AndroidDriver class
        System.out.println(((AndroidDriver) appiumDriver).isDeviceLocked());
    }

    public void unlockDevice() {
        ((AndroidDriver) appiumDriver).unlockDevice(); // only available in AndroidDriver class
    }
}
