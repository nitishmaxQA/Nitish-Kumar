package utils;

import config.Configreader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Helper class to perform common UI actions for Android and iOS apps.
 */
public class ActionHelper {
    private AppiumDriver driver;
    private AndroidDriver androidDriver;
    private IOSDriver iosDriver;
    private String platformType;
    private static final int RANDOM_STRING_LENGTH = 10;
    private static final Logger logger = Logger.getLogger(ActionHelper.class.getName());

    // Constructor initializes driver and platform type
    public ActionHelper(AppiumDriver driver, String platformType) {
        this.driver = driver;
        this.platformType = platformType;
        if ("android".equalsIgnoreCase(platformType)) {
            this.androidDriver = (AndroidDriver) driver;
        } else if ("ios".equalsIgnoreCase(platformType)) {
            this.iosDriver = (IOSDriver) driver;
        } else {
            throw new IllegalStateException("Unsupported platform type: " + platformType);
        }
    }

    // Click on an element directly by locator
    public void click1(By locator) {
        driver.findElement(locator).click();
    }

    // Press Android back button
    public void pressBack() {
        androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    // Press Android enter key
    public void pressEnter() {
        androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    // Click a WebElement if it is interactable
    public static void clickElement(WebElement element) {
        try {
            if (element != null && element.isDisplayed() && element.isEnabled()) {
                element.click();
            } else {
                System.out.println("Element is not interactable.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Find and return a WebElement
    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    // Pause the execution for specified seconds
    public static void gotoSleep(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Generate a random integer between a range
    public static int generateRandomIntBetweenRange(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    // Retrieve internal value from a By locator using reflection
    public static String getFieldValue(By by) {
        String value = "";
        try {
            Field field = by.getClass().getDeclaredFields()[0];
            field.setAccessible(true);
            value = field.get(by).toString();
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }

    // Wait until an element is clickable and return it
    public WebElement waitUntilClickabilityOfElement(By by) {
        String className = by.getClass().getSimpleName();
        String value = getFieldValue(by);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (TimeoutException e) {
            String errorString = "Timed out waiting for clickability of element [" + className + " = " + value + "]";
            throw new ElementNotInteractableException(errorString);
        }
    }

    // Click an element after waiting until it becomes clickable
    public void click(By by) {
        waitUntilClickabilityOfElement(by).click();
        logger.info("Clicked element: " + by.toString());
    }

    // Wait until an element is visible and return it
    public WebElement waitUntilVisibilityOfElement(By by) {
        String className = by.getClass().getSimpleName();
        String value = getFieldValue(by);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            String errorString = "Timed out waiting for visibility of element [" + className + " = " + value + "]";
            throw new ElementNotInteractableException(errorString);
        }
    }

    // Get text from a visible element
    public String getText(By by) {
        String text = waitUntilVisibilityOfElement(by).getText();
        System.out.println("[INFO] Fetched text: '" + text + "' from element: " + by.toString());
        return text;
    }

    // Get current running package name (Android only)
    public String getCurrentPackage() {
        String currentPackage = androidDriver.getCurrentPackage();
        System.out.println("Current Package: " + currentPackage);
        return currentPackage;
    }

    // Parse and convert amount text into integer (removing symbols)
    public static int parseAmount(String text) {
        text = text.replaceAll("[^\\d.-]", "");
        double val = Double.parseDouble(text);
        return (int) val;
    }

    // Relaunch the application using package name from config
    public void Relaunchapp() {
        String packageName = Configreader.get("app.package");
        androidDriver.terminateApp(packageName);
        androidDriver.activateApp(packageName);
    }

    // Perform platform-specific actions like logging current activity or bundle ID
    public void performPlatformSpecificAction() {
        if ("android".equalsIgnoreCase(platformType)) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            System.out.println("Android Current Activity: " + androidDriver.currentActivity());
        } else if ("ios".equalsIgnoreCase(platformType)) {
            IOSDriver iosDriver = (IOSDriver) driver;
            System.out.println("iOS Bundle ID: " + iosDriver.getCapabilities().getCapability("bundleId"));
        } else {
            throw new IllegalStateException("Unknown platform type.");
        }
    }
}
