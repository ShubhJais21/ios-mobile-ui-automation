package screens.ride;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.CommonAction;

public class FareEstimate {
    private AppiumDriver driver;
    private CommonAction commonAction;

    @FindBy(xpath = "//android.widget.TextView[@text='Bike']")
    private WebElement selectLink;

    @FindBy(xpath = "//android.widget.TextView[@text='Book Bike']")
    private WebElement selectBook;

    @FindBy(xpath = "//android.widget.TextView[@text='Book Auto']")
    private WebElement selectBookAuto;

    @FindBy(xpath = "//android.widget.TextView[@text='Auto']")
    private WebElement selectAuto;

    @FindBy(xpath = "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.Button")
    private WebElement bookRide;

    private WebElement selectService;

    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"Choose payment method\"]")
    private WebElement defaultPaymentMode;

    public FareEstimate(AppiumDriver driver) {
        commonAction = new CommonAction(driver);
        this. driver= driver;
        PageFactory.initElements(driver,this);
    }

    @Step("selects service in Fare Estimate screen and book")
    public CaptainSearch userSelectServiceAsLinkAndBookRide() {
        commonAction.waitForSomeTime(4000);
        commonAction.waitForElementToBeClickable(selectLink);
        selectLink.click();
        //commonAction.waitForSomeTime(2000);
        commonAction.waitForElementToBeClickable(selectBook);
        // commonAction.waitForSomeTime(2000);
        selectBook.click();
        //commonAction.waitForSomeTime(10000);
        return new CaptainSearch(driver);
    }

    @Step("selects auto service in Fare Estimate screen and book")
    public CaptainSearch userSelectServiceAsAutoAndBookRide() {
        commonAction.waitForSomeTime(2000);
        commonAction.waitForElementToBeClickable(selectAuto);
        selectAuto.click();
        // commonAction.waitForSomeTime(2000);
        commonAction.waitForElementToBeClickable(selectBookAuto);
        // commonAction.waitForSomeTime(2000);
        selectBookAuto.click();
        //commonAction.waitForSomeTime(10000);
        return new CaptainSearch(driver);
    }

    public FareEstimate verifyFareEstimateIsShown(){
        commonAction.waitForElementToBeVisible(selectBook);
        Assert.assertTrue(selectBook.isDisplayed(),"Book bike option is not displayed");
        Assert.assertTrue(selectAuto.isDisplayed(),"Book auto option is not displayed");
        return this;
    }

//    @Step("Check payment mode is selected or not")
//    public FareEstimate checkPaymentMode(){
//        if(defaultPaymentMode.isDisplayed()){
//            userSelectOnPaymentMode();
//        }
//        return this;
//    }

    @Step("Selects Payment Mode")
    public PaymentMode userOpenPaymentScreen() {
        commonAction.waitForElementToBeClickable(defaultPaymentMode);
        defaultPaymentMode.click();
        return new PaymentMode(driver);
    }

}
