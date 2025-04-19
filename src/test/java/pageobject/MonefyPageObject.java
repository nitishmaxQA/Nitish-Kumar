package pageobject;

import org.openqa.selenium.By;

public class MonefyPageObject {
    private MonefyPageObject instance = new MonefyPageObject();

    public static String ExpectedTextNotFound="No such text found,please check again";
    public static String CTAtext="This CTA is not present, please check";
    public static By IntroScreenCTA =By.id("com.monefy.app.lite:id/buttonContinue");
    public static By introScreenHeaderText =By.id("com.monefy.app.lite:id/textViewHeader");
    public static String  IntroScreen1Text = "Say hi to your new finance tracker";
    public static String IntroScreen2Text = "Control your spend and start saving";
    public static String IntroScreen3Text = "Great! Want us to send you small reminders?";
    public static String IntroScreen4Text = "Together we’ll reach your financial goals";
    public static By WelcomeOfferScreen = By.id("com.monefy.app.lite:id/textViewTitle");
    public static String WelcomeOfferScreenText = "Claim your one-time welcome offer";
    public static By PurchaseButton= By.id("com.monefy.app.lite:id/buttonPurchase");
    public static String PurchaseCTAText = "CLAIM MY OFFER";
    public static By PaymentOption = By.id("com.android.vending:id/0_resource_name_obfuscated");
    public static String PaymentOptionTitle = "Add payment method to your Google Account";
    public static String FailureText = "Error ...Payment option prompt is not found";
    public static String packageName = "com.android.vending";
    public static By OfferCountdown = By.id("com.monefy.app.lite:id/textViewCountdown");
    public static By CloseButton = By.id("com.monefy.app.lite:id/buttonClose");
    public static By expense_button = By.id("com.monefy.app.lite:id/expense_button");
    public static By income_button = By.id("com.monefy.app.lite:id/income_button");
    public static By keyboard1 = By.id("com.monefy.app.lite:id/buttonKeyboard1");
    public static By keyboard2 = By.id("com.monefy.app.lite:id/buttonKeyboard2");
    public static By keyboard3 = By.id("com.monefy.app.lite:id/buttonKeyboard3");
    public static By ChooseCategory = By.id("com.monefy.app.lite:id/keyboard_action_button");
    public static By expense_car = By.xpath("//android.widget.TextView[@text='Car']");
    public static By income_salary= By.xpath("//android.widget.TextView[@text='Salary']");
    public static By balance = By.id("com.monefy.app.lite:id/balance_amount");
    public static By restore_purchase = By.id("com.monefy.app.lite:id/buttonRestore");
    public static By incomeamount = By.id("com.monefy.app.lite:id/amount_text");


    public MonefyPageObject getInstance() {

        return instance;
    }
}
