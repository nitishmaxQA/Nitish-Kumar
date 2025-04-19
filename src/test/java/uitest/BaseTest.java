package uitest;

import executionengine.DriverManager;
import org.testng.asserts.SoftAssert;
import utils.ActionHelper;
import config.Configreader;
import customreporter.CustomReporter;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.*;

import java.time.Duration;

// Listener to attach custom reporting to the test execution
@Listeners({CustomReporter.class})
public class BaseTest {
    protected AppiumDriver driver;
    protected ActionHelper actionHelper;
    protected String platformType;
    SoftAssert softAssert;

    @BeforeMethod
    public void init() {
        softAssert = new SoftAssert();
    }

    /**
     * Setup method to initialize the Appium driver before test execution starts.
     */
    @BeforeSuite
    public void setup() {
        platformType = Configreader.get("platform"); // Read platform type dynamically
        if (platformType == null || platformType.isEmpty()) {
            throw new IllegalStateException("Platform type is not specified in the config file.");
        }
        driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        actionHelper = new ActionHelper(driver, platformType); // Initialize action helper
    }

    /**
     * Tear down method to quit the driver after test execution.
     */
    @AfterClass
    public void teardown() {
        DriverManager.teardown(); // Quit driver and clean up
    }
}
