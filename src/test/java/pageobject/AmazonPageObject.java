package pageobject;

import org.openqa.selenium.By;

public class AmazonPageObject {
    private AmazonPageObject instance = new AmazonPageObject();

    public static String ExpectedTextNotFound="No such text found,please check again";
    public static String CTAtext="This CTA is not present, please check";
    public static By EnterTextInSearchField = By.id("in.amazon.mShop.android.shopping:id/rs_search_src_text");
    public static By SearchField = By.id("in.amazon.mShop.android.shopping:id/chrome_search_hint_view");
    public static By SelectLanguageEnglish = By.xpath("//android.widget.ImageView[@content-desc='Select English']");
    public static By ContinueButton = By.id("in.amazon.mShop.android.shopping:id/continue_button");
    public static By SkipButton = By.id("in.amazon.mShop.android.shopping:id/skip_sign_in_button");
    public static By iphonePrice=By.xpath("//android.widget.TextView[contains(@text, '₹')]");




    public AmazonPageObject getInstance() {
        return instance;
    }
}
