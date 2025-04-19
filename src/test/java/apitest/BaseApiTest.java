package apitest;

import customreporter.CustomReporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

@Listeners({CustomReporter.class})
public class BaseApiTest {

    SoftAssert softAssert;

    @BeforeMethod
    public void init() {
        softAssert = new SoftAssert();
    }
}
