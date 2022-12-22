package cucumber_step_defs;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TestBasis {
    public static AppiumDriver<MobileElement> appiumDriver;
    public static WebDriverWait wait;

    private DesiredCapabilities capabilities = new DesiredCapabilities();
    private URL serverAddress;
    private AppiumDriverLocalService service;

    private static Map<String, String> deviceSettings;
    private Map<String, Map<String, String>> deviceMaps;
    private String deviceName;

    public static String appPackage = "io.appium.android.apis";
    public static String appPath;
    public static String port;

    public static String platformSelector = System.getProperty("platform", "iOS_emulator");

    @Before
    public void deviceStart() throws Exception {
        initDeviceMaps();
        switch (platformSelector) {
            case "Android_emulator":
                deviceName = System.getProperty("device", "android_Pixel4_local");
                service = AppiumDriverLocalService.buildDefaultService();
                setCapabilities();
                appPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                        + File.separator + "resources" + File.separator + deviceSettings.get("appName");
                capabilities.setCapability(MobileCapabilityType.APP, appPath);
                capabilities.setCapability("noReset", "true");
                capabilities.setCapability("fullReset", "false");
                // для автоматического запуска эмулятора
                capabilities.setCapability("avd", deviceSettings.get("avdName"));
                capabilities.setCapability("avdLaunchTimeout", 200000);
                // сколько сохранять активность сессии в дебаге
                capabilities.setCapability("newCommandTimeout", 300);  //5 minutes
                capabilities.setCapability("appPackage", deviceSettings.get("appPackage"));
                // для разблокировки экрана
                capabilities.setCapability("unlockType", "pin");
                capabilities.setCapability("unlockKey", "0000");
                break;
            case "iOS_emulator":
                deviceName = System.getProperty("device", "iPhone_13_local");
                service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                        .usingDriverExecutable(new File("/usr/local/bin/node"))
                        .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                        .usingPort(4724)
                        .withArgument(GeneralServerFlag.SESSION_OVERRIDE));
                setCapabilities();
                // (не обязательно) для того чтобы УСТАНОВИТЬ ПРИЛОЖЕНИЕ (каждый раз при запуске кода)
//        appPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
//                + File.separator + "resources" + File.separator + deviceSettings.get("appName");
//        capabilities.setCapability(MobileCapabilityType.APP, appPath);
                capabilities.setCapability("simulatorStartupTimeout", 180000);  //3 minutes
                capabilities.setCapability("bundleId", deviceSettings.get("wdaBundleId"));
                // позволяет видеть все контексты в Сафари для гибридынх страниц
                capabilities.setCapability("includeSafariInWebviews", true);
                break;
        }
//        serverAddress = new URL("http://127.0.0.1:" + port + "/wd/hub");
        serverAddress = new URL("http://127.0.0.1:4723/wd/hub");
        service.start();
        initializeDriver();
    }

    @After
    public void afterClassSingleDeviceRun() {
        appiumDriver.quit();
        service.stop();
    }

    public static Map<String, String> getDeviceSettings() {
        return deviceSettings;
    }

    private void initializeDriver() {
        switch (platformSelector) {
            case "Android_emulator":
                appiumDriver = new AndroidDriver<>(serverAddress, capabilities);
                break;
            case "iOS_emulator":
                appiumDriver = new IOSDriver<>(serverAddress, capabilities);
                break;
        }
        wait = new WebDriverWait(appiumDriver, 10);
    }

    private void initDeviceMaps() throws IOException {
        InputStream stream = TestBasis.class.getResourceAsStream("/Devices.json");
        if (stream == null) {
            throw new RuntimeException("Could not find Devices.json");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        deviceMaps = new ObjectMapper().enable(JsonParser.Feature.ALLOW_COMMENTS).readValue(reader, HashMap.class);
        stream.close();
    }

    private void setCapabilities() {
        deviceSettings = deviceMaps.get(deviceName);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceSettings.get("deviceName"));
        capabilities.setCapability(MobileCapabilityType.UDID, deviceSettings.get("udid"));
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, deviceSettings.get("platformName"));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, deviceSettings.get("automationName"));
        // not necessary (waits for webview to connect)
        capabilities.setCapability("webviewConnectTimeout", 9000);
    }

}
