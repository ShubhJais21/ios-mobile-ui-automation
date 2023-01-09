package screens.loginandregistration;

import com.rapido.api.customer.entities.Location;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.CommonAction;

public class LoginScreen {
    private  AppiumDriver driver;
    private CommonAction commonAction;
    public LoginScreen(AppiumDriver driver) {
        PageFactory.initElements(driver, this);
        commonAction = new CommonAction(driver);
        this.driver = driver;
    }
    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"Get Started \"]")
    private WebElement getStartedButton;

    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"Continue\"]")
    protected WebElement continueButton;

    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"Allow While Using App\"]")
    protected WebElement allowLocation;

    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"Allow\"]")
    protected WebElement allowNotification;

    @FindBy(xpath = "//XCUIElementTypeOther/XCUIElementTypeTextField")
    private WebElement enterMobileNumber;

    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"Send OTP\"]")
    private WebElement sendOtpButton;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Enter Phone Number \"]")
    private WebElement loginScreenTitle;

    public void tapOnGetStarted() {
        try {
            commonAction.waitForElementToBeClickable(getStartedButton);
            getStartedButton.click();
        } catch (StaleElementReferenceException e) {
            commonAction.waitForElementToBeClickable(getStartedButton);
            getStartedButton.click();
        }
    }

    public void tapOnContinue() {
        commonAction.waitForElementToBeClickable(continueButton);
        continueButton.click();
    }

    @Step("Customer enters mobile number and submit")
    public VerifyOtpScreen enterMobileNumberAndSubmit(String mobileNumber) {
        commonAction.waitForElementToBeClickable(enterMobileNumber);
        enterMobileNumber.click();
        enterMobileNumber.sendKeys(mobileNumber);
        commonAction.hideKeyboard();
        commonAction.waitForElementToBeClickable(sendOtpButton);
        sendOtpButton.click();
        commonAction.waitForSomeTime(2000);
        return new VerifyOtpScreen(driver);
    }

    public void tapOnAllowLocation() {
        try {
            commonAction.waitForElementToBeClickable(allowLocation);
            allowLocation.click();
        } catch (StaleElementReferenceException e) {
            commonAction.waitForElementToBeClickable(allowLocation);
            allowLocation.click();
        }
    }

    public void tapOnAllowNotification() {
        commonAction.waitForElementToBeClickable(allowNotification);
        allowNotification.click();
    }

    public LoginScreen getStartedOnStartUp()
    {
        tapOnGetStarted();
        tapOnContinue();
        tapOnAllowLocation();
        tapOnAllowNotification();
        return this;
    }

    public LoginScreen setCustomerAppCurrentLocation(Location location){
        org.openqa.selenium.html5.Location currentLocation = new org.openqa.selenium.html5.Location(location.getLat(),location.getLng(), 0);
        driver.setLocation(currentLocation);
        return this;
    }

    @Step("Customer is in Login Screen")
    public LoginScreen verifyUserInLoginScreen()
    {
        commonAction.waitForElementToBeVisible(loginScreenTitle);
        String loginScreenText= loginScreenTitle.getText();
        Assert.assertEquals("Enter Phone Number ",loginScreenText,"Login Screen Text is not matching");
        return new LoginScreen(driver);
    }
}
