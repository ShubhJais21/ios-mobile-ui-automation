package screens.loginandregistration;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import screens.home.HomeScreen;
import utils.CommonAction;

public class ProfileScreen {
    private  AppiumDriver driver;
    private CommonAction commonAction;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Enter Full Name\"]//following::XCUIElementTypeTextField[1]")
    private WebElement nameInputBox;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Enter Email\"]//following::XCUIElementTypeTextField[1]")
    private WebElement emailInputBox;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Male\"]")
    private WebElement genderMCheckBox;

    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"Submit\"]")
    private WebElement submitButton;
    @FindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Create your profile\"]")
    private WebElement profilePageText;

    @FindBy(xpath = "//android.widget.TextView[@text='Allow location access']")
    private WebElement locationAccess;

    @FindBy(xpath = "//android.widget.Button[@text='Allow access']")
    private WebElement allowAccess;
    public ProfileScreen(AppiumDriver driver) {
        PageFactory.initElements(driver, this);
        commonAction = new CommonAction(driver);
        this.driver = driver;
    }

    @Step("Customer enters profile details")
    public HomeScreen enterProfileDetails(String name, int length) {
        // commonAction.waitForSomeTime(2000);
        commonAction.waitForElementToBeVisible(nameInputBox);
        //nameInputBox.click();
        nameInputBox.sendKeys(name);
        emailInputBox.sendKeys(commonAction.generateRandomEmail(length));
        genderMCheckBox.click();
        commonAction.hideKeyboard();
        commonAction.waitForElementToBeVisible(submitButton);
        submitButton.click();
        return new HomeScreen(driver);
    }

    @Step("Customer is shown location Page")
    public ProfileScreen verifyLocationPermissionPage() {
        commonAction.waitForSomeTime(10000);
        commonAction.waitForElementToBeVisible(locationAccess);
        Assert.assertEquals("Allow location access", locationAccess.getText());
        return this;
    }

    @Step("Customer allows location access")
    public ProfileScreen allowAccessToLocation() {
        commonAction.waitForElementToBeVisible(locationAccess);
        allowAccess.click();
        return this;
    }

    @Step("Customer is shown Profile page")
    public ProfileScreen verifyProfilePageIsDisplayed() {
        //commonAction.waitForSomeTime(5000);
        commonAction.waitForElementToBeVisible(profilePageText);
        Assert.assertEquals("Create your profile", profilePageText.getText(),"User is not Profile page or the header of Profile Page is not matching");
        return this;
    }
}
