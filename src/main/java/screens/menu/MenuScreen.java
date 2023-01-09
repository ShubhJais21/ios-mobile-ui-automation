package screens.menu;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import screens.menu.SettingsScreen;
import utils.CommonAction;

public class MenuScreen {

    private AppiumDriver driver;
    private CommonAction commonAction;

    public MenuScreen(AppiumDriver driver) {
        commonAction = new CommonAction(driver);
        this. driver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "(//XCUIElementTypeImage[@name=\"icon_accessory_mini\"])[1]")
    private WebElement profileSettings;

    @Step("Click on Setting")
    public SettingsScreen goToProfileSettingsScreen(){
        commonAction.waitForSomeTime(2000);
        profileSettings.click();
        return new SettingsScreen(driver);
    }



}
