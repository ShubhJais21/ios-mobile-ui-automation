package screens.menu;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonAction;

public class SettingsScreen {
    private AppiumDriver driver;
    private CommonAction commonAction;

    public SettingsScreen(AppiumDriver driver) {
        commonAction = new CommonAction(driver);
        this. driver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//XCUIElementTypeNavigationBar[@name=\"Profile\"]//following::XCUIElementTypeCell[5]//XCUIElementTypeOther[2]")
    private WebElement logoutButton;


    @Step("Click On Logout")
    public LogoutScreen logout(){
        commonAction.waitForElementToBeClickable(logoutButton);
        logoutButton.click();
        return new LogoutScreen(driver);
    }
}
