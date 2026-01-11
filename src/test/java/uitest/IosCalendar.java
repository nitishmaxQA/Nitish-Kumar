package uitest;

import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

public class IosCalendar extends  BaseTest{

    @Test
    public void test(){
        actionHelper.click(AppiumBy.accessibilityId("Current month, January 2026"));
    }
}
