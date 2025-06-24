package executionengine;

import config.Configreader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * DriverManager class handles the Appium server and driver initialization for Android and iOS platforms.
 */
public class DriverManager {

    private static AppiumDriver driver; // Shared AppiumDriver instance
    private static AppiumDriverLocalService service; // Appium server instance
    private static final Logger logger = Logger.getLogger(DriverManager.class.getName());

    // Private constructor to prevent object creation
    private DriverManager() {
    }

    /**
     * Returns the initialized AppiumDriver instance.
     * Starts server and driver if not already started.
     */
    public static synchronized AppiumDriver getDriver() {
        if (driver == null) {
            startServer(Configreader.get("platform"));
        }
        return driver;
    }

    /**
     * Starts the Appium server and initializes the driver based on platform type.
     */
    private static void startServer(String platform) {
        if (service == null || !service.isRunning()) {
            startAppiumService();
        }

        try {
            if ("android".equalsIgnoreCase(platform)) {
                initializeAndroidDriver();
            } else if ("ios".equalsIgnoreCase(platform)) {
                initializeIOSDriver();
            } else {
                throw new IllegalArgumentException("Invalid platform specified: " + platform);
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // Global implicit wait
        } catch (Exception e) {
            logger.severe("Error starting the driver: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Starts the Appium local server.
     */
    private static void startAppiumService() {
        HashMap<String, String> environment = new HashMap<>();

        // Set ANDROID_HOME from system environment or from config file
        String androidHome = System.getenv("ANDROID_HOME");
        if (androidHome == null) {
            androidHome = Configreader.get("ANDROID_HOME");
        }
        environment.put("ANDROID_HOME", androidHome);

        service = new AppiumServiceBuilder()
                .withAppiumJS(new File(Configreader.get("AppiumJSPath")))
                .withArgument(GeneralServerFlag.LOG_LEVEL, "error")
                .usingAnyFreePort()
                .withEnvironment(environment)
                .build();

        service.start();
    }

    /**
     * Initializes the AndroidDriver with required capabilities.
     */
    private static void initializeAndroidDriver() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAppActivity(Configreader.get("app.activity"));
        options.setAppPackage(Configreader.get("app.package"));
        options.setNoReset(false);
        options.setCapability("appium:logLevel", "error");

        driver = new AndroidDriver(service.getUrl(), options);
        logger.info("Android driver initialized.");
    }

    /**
     * Initializes the IOSDriver with required capabilities.
     */
    private static void initializeIOSDriver() {
        XCUITestOptions options = new XCUITestOptions()
                .setDeviceName(Configreader.get("deviceName"))
                .setBundleId(Configreader.get("bundle.id"))
                .setPlatformVersion(Configreader.get("platform.version"));

        driver = new IOSDriver(service.getUrl(), options);
        logger.info("iOS driver initialized.");
    }

    /**
     * Quits the driver and stops the Appium server.
     */
    public static void teardown() {
        logger.info("Driver is: " + driver);

        if (driver != null) {
            driver.quit();
            logger.info("Driver quit successfully.");
        }

        if (service != null && service.isRunning()) {
            service.stop();
            logger.info("Appium service stopped.");
        }
    }
}
