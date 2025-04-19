package customreporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import config.Configreader;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Custom TestNG listener to generate Extent Reports for API and Appium tests.
 */
public class CustomReporter implements ITestListener {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal();

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test suite started: " + context.getName());
        String reportName = "ExtentReport.html";

        // Choose different report names based on the test suite
        if (context.getName().equals("monefyTest")) {
            reportName = "apptest_report.html"; // App test report
        } else if (context.getName().equals("API Tests")) {
            reportName = "apitest_report.html"; // Api test report
        }
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + reportName);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        // Add environment and user info to the report
        extent.setSystemInfo("Environment", Configreader.get("Environment"));
        extent.setSystemInfo("User", Configreader.get("username"));
    }

    /**
     * Invoked when each test starts.
     * Creates a new ExtentTest entry for the running test.
     */
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName());
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    /**
     * Invoked when a test passes successfully.
     * Logs a PASS status in the Extent Report.
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
        test.get().pass("Test passed");
    }

    /**
     * Invoked when a test fails.
     * Logs a FAIL status along with the exception in the Extent Report.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
        test.get().fail(result.getThrowable());
    }

    /**
     * Invoked when a test is skipped.
     * Logs a SKIP status along with the reason.
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
        test.get().skip(result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test failed but within success percentage: " + result.getName());
    }

    /**
     * Invoked after the test suite finishes.
     * writes all information to the Extent Report.
     */
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test suite finished: " + context.getName());
        extent.flush();
    }
}