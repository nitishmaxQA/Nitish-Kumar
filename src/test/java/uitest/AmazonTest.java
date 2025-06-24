package uitest;

import org.openqa.selenium.WebElement;
import pageobject.AmazonPageObject;

import org.testng.annotations.Test;

import java.util.List;

public class AmazonTest extends BaseTest {



    /**
     * Test Case 1:
     * Launch app
     * navigate to home screen and perform search
     */
    @Test(description = "Search_iPhone_from_searchField")
    public void Test_01_Search_iPhone_from_searchField() {

        // Intro Screen 1
        actionHelper.click(AmazonPageObject.SelectLanguageEnglish);
        actionHelper.click(AmazonPageObject.ContinueButton);
        actionHelper.click(AmazonPageObject.SkipButton);
        actionHelper.click(AmazonPageObject.SearchField);
        actionHelper.fill(AmazonPageObject.EnterTextInSearchField, "iPhone 16");
        actionHelper.pressEnter();
        actionHelper.gotoSleep(2000);

    }

    /**
     * Test Case 2:
     * Print first iphone price from search results .
     *
     */

    @Test(description = "Verify first iPhone price from search results")
    public void Test_02_Verify_iPhone_prices() {

      //  WebElement priceElement = driver.findElement(AmazonPageObject.iphonePrice);
        actionHelper.scrollOnetime();
        List<WebElement> priceElements = actionHelper.findElements(AmazonPageObject.iphonePrice);
        if (!priceElements.isEmpty()) {
            String firstPrice = priceElements.get(0).getText().trim();
            System.out.println("First iPhone 16 Price: " + firstPrice);
        } else {
            System.out.println("No prices found.");
        }
    }



}
