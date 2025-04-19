package uitest;

import pageobject.MonefyPageObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MonefyTest extends BaseTest {



    /**
     * Test Case 1:
     * Verify each Intro screen shows correct text,
     * each CTA navigates to the next Intro screen,
     */
    @Test(description = "Verify each Intro screen shows correct text, each CTA navigates to the next Intro screen, "
            + "and clicking on final intro screen CTA loads the Welcome Offer screen")
    public void Test_01_verify_introScreenText_and_CTA_funtionality() {

        // Intro Screen 1
        softAssert.assertEquals(
                actionHelper.getText(MonefyPageObject.introScreenHeaderText),
                MonefyPageObject.IntroScreen1Text,
                MonefyPageObject.ExpectedTextNotFound);
        actionHelper.click(MonefyPageObject.IntroScreenCTA);

        // Intro Screen 2
        softAssert.assertEquals(
                actionHelper.getText(MonefyPageObject.introScreenHeaderText),
                MonefyPageObject.IntroScreen2Text,
                MonefyPageObject.ExpectedTextNotFound);
        actionHelper.click(MonefyPageObject.IntroScreenCTA);

        //Intro Screen 3
        softAssert.assertEquals(
                actionHelper.getText(MonefyPageObject.introScreenHeaderText),
                MonefyPageObject.IntroScreen3Text,
                MonefyPageObject.ExpectedTextNotFound);
        actionHelper.click(MonefyPageObject.IntroScreenCTA);

        //Intro Screen 4
        softAssert.assertEquals(
                actionHelper.getText(MonefyPageObject.introScreenHeaderText),
                MonefyPageObject.IntroScreen4Text,
                MonefyPageObject.ExpectedTextNotFound);

        softAssert.assertAll();
    }

    /**
     * Test Case 2:
     * Verify 'intro text screen's final CTA navigates to welcome offer screen.
     * Validating purchase button text and welcome offer screen text
     */

    @Test(description = "Verify Clicking on fina CTA loads welcome offer screen")
    public void Test_02_Verify_Clicking_on_finalCTA_loads_welcome_offer_screen() {
        actionHelper.click(MonefyPageObject.IntroScreenCTA);
        // Welcome Offer Screen
        softAssert.assertEquals(
                actionHelper.getText(MonefyPageObject.WelcomeOfferScreen),
                MonefyPageObject.WelcomeOfferScreenText,
                MonefyPageObject.ExpectedTextNotFound);
        softAssert.assertEquals(
                actionHelper.getText(MonefyPageObject.PurchaseButton),
                MonefyPageObject.PurchaseCTAText,
                MonefyPageObject.CTAtext);
        softAssert.assertAll();
    }

    /**
     * Test Case 3:
     * Verify 'Claim Offer' CTA navigates to Payment Options if not subscribed,
     * validate android OS package name of payment prompt.
     */
    @Test(description = "Verify 'Claim Offer' CTA navigates to Payment Options if not subscribed")
    public void Test_03_Verify_Claim_Offer_CTA_navigatesToPayment_Options_if_not_subscribed() {
        actionHelper.click(MonefyPageObject.PurchaseButton);
        actionHelper.gotoSleep(2000); // Wait for screen to load
        // Validate google OS payment prompt by validating by package name
        Assert.assertEquals(actionHelper.getCurrentPackage(), MonefyPageObject.packageName);
        actionHelper.pressBack();
        actionHelper.pressBack(); // Go back to home screen

    }

    /**
     * Test Case 4:
     * Ensure Offer Countdown Timer is working by checking that time changes after a delay.
     */
    @Test(enabled = true, description = "Ensure Offer End Timer is running")
    public void Test_04_verify_Offer_Countdown_Timer_is_working_by_checking_that_time_changes_after_a_delay() {
        actionHelper.Relaunchapp();
        String initialTime = actionHelper.getText(MonefyPageObject.OfferCountdown);
        actionHelper.gotoSleep(2000);
        String updatedTime = actionHelper.getText(MonefyPageObject.OfferCountdown);
        Assert.assertNotEquals(updatedTime, initialTime, "Timer is not running.");
        System.out.println("Timer is running! Initial: " + initialTime + " | Updated: " + updatedTime);
    }

    /**
     * Test Case 5:
     * Verify user can add expense/income by selecting categories,
     * and the balance is updated correctly. Adding 12 as expense and 13 as income
     * final update balance should be 1
     */
    @Test(description = "Verify user can add expense/income by choosing category and balance is updated correctly")
    public void Test_05_verify_user_can_add_expensesIncome_and_balance_is_updated() {

        actionHelper.click(MonefyPageObject.CloseButton); //Move to add expenses/income screen
        // Add an expense of 12 as car
        actionHelper.click(MonefyPageObject.expense_button);
        actionHelper.click(MonefyPageObject.keyboard1);
        actionHelper.click(MonefyPageObject.keyboard2);
        actionHelper.click(MonefyPageObject.ChooseCategory);
        actionHelper.click(MonefyPageObject.expense_car);
        actionHelper.gotoSleep(3000); // Wait for expensed to be added
        String initial_balance = actionHelper.getText(MonefyPageObject.balance);// Get initial balance after adding expenses
        int initialBalance = actionHelper.parseAmount(initial_balance);
        System.out.println(initial_balance);
        // Add an income of 13 as salary
        actionHelper.click(MonefyPageObject.income_button);
        actionHelper.click(MonefyPageObject.keyboard1);
        actionHelper.click(MonefyPageObject.keyboard3);
        String incomeadded = actionHelper.getText(MonefyPageObject.incomeamount);
        actionHelper.click(MonefyPageObject.ChooseCategory);
        actionHelper.click(MonefyPageObject.income_salary);
        // Calculate actual balance after adding income
        int income = actionHelper.parseAmount(incomeadded);
        int expectedBalance = income + initialBalance; // Validating balance should be +1 (13-12=1)
        System.out.println("Updated balance is " + expectedBalance);
        String actualbalance = actionHelper.getText(MonefyPageObject.balance);
        int actual_balance = actionHelper.parseAmount(actualbalance);
        //Validating balance is updated as per added expenses and income
        Assert.assertEquals(actual_balance, expectedBalance, "Balance is not updated correctly!");

    }

}
