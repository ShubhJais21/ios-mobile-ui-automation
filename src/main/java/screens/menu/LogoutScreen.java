package screens.menu;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import screens.loginandregistration.LoginScreen;
import screens.menu.SettingsScreen;
import utils.CommonAction;

public class LogoutScreen {
    private AppiumDriver driver;
    private CommonAction commonAction;

    public LogoutScreen(AppiumDriver driver) {
        PageFactory.initElements(driver, this);
        commonAction = new CommonAction(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"Yes\"]")
    private WebElement logoutConfirm;

    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"Cancel\"]")
    private WebElement cancelButton;


    @Step("Click On Confirm Logout button")
    public LoginScreen confirmLogout(){
        commonAction.waitForElementToBeClickable(logoutConfirm);
        logoutConfirm.click();
        return new LoginScreen(driver);
    }

    @Step("Click On GoBack button from logout screen")
    public SettingsScreen clickCancelButton(){
        commonAction.waitForElementToBeClickable(cancelButton);
        cancelButton.click();
        return new SettingsScreen(driver);
    }

}
