package cucumber_step_defs;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;

public class TestBasis {
    public static AppiumDriver<MobileElement> appiumDriver;
    public static WebDriverWait wait;

    private DesiredCapabilities capabilities = new DesiredCapabilities();
    private URL serverAddress;
    private AppiumDriverLocalService service;

    public static String appPackage = "io.appium.android.apis";
    public static String appPath;
    public static String port;
    public static String device = System.getProperty("device", "nexus");

    @Before
    public void deviceStart() throws Exception {
        service = AppiumDriverLocalService.buildDefaultService();
        deviceChoose();
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        // (не обязательно) для того чтобы УСТАНОВИТЬ ПРИЛОЖЕНИЕ (каждый раз при запуске кода) как на андроид так и на айос
        appPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "ApiDemos-debug.apk";
        capabilities.setCapability(MobileCapabilityType.APP, appPath);
        // для автоматического запуска эмулятора
        capabilities.setCapability("avdLaunchTimeout", 200000);
        // сколько сохранять активность сессии в дебаге
        capabilities.setCapability("newCommandTimeout", 300);  //5 minutes
        capabilities.setCapability("appPackage", appPackage);
        // для разблокировки экрана
        capabilities.setCapability("unlockType", "pin");
        capabilities.setCapability("unlockKey", "0000");
        capabilities.setCapability("appActivity", "io.appium.android.apis.ApiDemos");

        serverAddress = new URL("http://127.0.0.1:" + port + "/wd/hub");
        service.start();
        initializeDriver();
    }

    private void deviceChoose() {
        switch (device){
            case "pixel": capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 4 API 30");
                capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
                capabilities.setCapability("avd", "Pixel_4_API_30");
                port="4723";
                break;
            case "nexus": capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 5X API 31");
                capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5556");
                capabilities.setCapability("avd", "Nexus_5X_API_31");
                port="4724";
                break;
        }
    }

    @After
    public void afterClassSingleDeviceRun() {
        appiumDriver.quit();
        service.stop();
    }

    private void initializeDriver() {
        appiumDriver = new AndroidDriver<>(serverAddress, capabilities);
        wait = new WebDriverWait(appiumDriver, 10);
    }
}
