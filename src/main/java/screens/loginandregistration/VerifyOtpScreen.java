package screens.loginandregistration;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import screens.home.HomeScreen;
import utils.CommonAction;

public class VerifyOtpScreen {

    public AppiumDriver driver;
    private CommonAction commonAction;

    public VerifyOtpScreen(AppiumDriver driver) {
        super();
        PageFactory.initElements(driver, this);
        this.driver = driver;
        commonAction = new CommonAction(driver);
    }

    @FindBy(xpath = "//XCUIElementTypeOther[1]/XCUIElementTypeTextField[1]")
    private WebElement otpScreen;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Incorrect OTP, please try again.\"]")
    private WebElement incorrectOtpMsg;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Resend OTP\"]")
    private WebElement resendOtp;

//    @FindBy(xpath = "//android.widget.TextView[@text='Send via Whatsapp']")
//    private WebElement resendWhatsAppOtp;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Enter the OTP\"]")
    private WebElement verifyOTP;

    @Step("New Customer enters correct Otp")
    public ProfileScreen enterOTPForNewUser(String otp) {
        enterOtp(otp);
        return new ProfileScreen(driver);
    }

    @Step("Existing Customer enters correct Otp")
    public HomeScreen enterOTPForExistingUser(String otp) {
        enterOtp(otp);
        return new HomeScreen(driver);
    }

    @Step("Otp is entered")
    public void enterOtp(String otp) {
        commonAction.waitForElementToBeVisible(otpScreen);
        otpScreen.clear();
        otpScreen.sendKeys(otp);
    }

    @Step("Customer enters incorrect Otp")
    public VerifyOtpScreen enterInvalidOTP() {
        commonAction.waitForSomeTime(3000);
        commonAction.waitForElementToBeVisible(otpScreen);
        //otpScreen.click();
        otpScreen.sendKeys("000000");
        return this;
    }


    @Step("Verify the customer gets the invalid otp error message")
    public VerifyOtpScreen verifyInvalidOtpErrorMessageIsShown() {
        commonAction.waitForElementToBeVisible(incorrectOtpMsg);
        Assert.assertEquals("Incorrect OTP, please try again.", incorrectOtpMsg.getText());
        return this;
    }

    @Step("Customer resends Otp sms")
    public VerifyOtpScreen clickSMSResendOtp() {
        commonAction.waitForSomeTime(15000);
        commonAction.waitForElementToBeClickable(resendOtp);
        resendOtp.click();
        commonAction.waitForSomeTime(3000);
        return this;
    }

//    @Step("Customer resends Otp WhatsApp")
//    public VerifyOtpScreen clickWhatsAppResendOtp() {
//        commonAction.waitForSomeTime(15000);
//        commonAction.waitForElementToBeClickable(resendWhatsAppOtp);
//        resendWhatsAppOtp.click();
//        commonAction.waitForSomeTime(2000);
//        return new VerifyOtpScreen(driver);
//    }

    @Step("Valiadte OTP screen")
    public VerifyOtpScreen validateOTPscreen(){
        commonAction.waitForSomeTime(3000);
        Assert.assertTrue(verifyOTP.isDisplayed(),"Verify OTP text is not showing");
        return this;
    }
}
