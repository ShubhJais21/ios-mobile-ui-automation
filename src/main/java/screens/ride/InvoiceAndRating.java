package screens.ride;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import screens.home.HomeScreen;
import utils.CommonAction;

public class InvoiceAndRating {
    private AppiumDriver driver;
    private CommonAction commonAction;

    public InvoiceAndRating(AppiumDriver driver){
        commonAction = new CommonAction(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//android.widget.TextView[@text='Pay with cash']")
    private WebElement payViaCash;

    @FindBy(xpath = "//android.view.View/android.widget.ScrollView/android.view.View[6]")
    private WebElement customerRating;

    @FindBy(xpath = "//android.view.View/android.view.View/android.widget.Button")
    private WebElement reviewSubmitButton;

    @FindBy(xpath = "//android.view.View/android.widget.ScrollView/android.widget.TextView[1]")
    private WebElement feedbackType;


    public InvoiceAndRating selectCashAspayment(){
        commonAction.waitForElementToBeVisible(payViaCash);
        payViaCash.click();
        return this;
    }

    public String rideRating(String rating){
        String ratingNo= driver.findElementByXPath("//android.view.View/android.widget.ScrollView/android.view.View["+rating+"][@text]").getText();
        driver.findElementByXPath("//android.view.View/android.widget.ScrollView/android.view.View["+rating+"]").click();
        return ratingNo;
    }

    public HomeScreen submitRideFeedback(){
        commonAction.waitForSomeTime(8000);
        customerRating.click();
        feedbackType.click();
        reviewSubmitButton.click();
        commonAction.waitForSomeTime(1000);
        return new HomeScreen(driver);
    }

}
