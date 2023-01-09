package screens.home;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import screens.menu.MenuScreen;
import utils.CommonAction;

import java.util.List;

public class HomeScreen {

    private AppiumDriver driver;
    private CommonAction commonAction;

    @FindBy(xpath = "//XCUIElementTypeTextField[not(contains(@value, 'Search'))]")
    private WebElement sourceAddress;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@value= 'Select From Map']")
    private WebElement selectOnMapButton;

    @FindBy(xpath = "//XCUIElementTypeTextField[@value ='Search Drop Location']")
    private WebElement destinationAddress;

    @FindBy(xpath = "//XCUIElementTypeCell")
    private List<WebElement> suggestionAddress;

    @FindBy(xpath = "//android.widget.TextView[@text='Where would you like to go?']")
    private WebElement searchButton;

    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"menu\"]")
    private WebElement profileIcon;
    public HomeScreen(AppiumDriver driver) {
        commonAction = new CommonAction(driver);
        this. driver= driver;
        PageFactory.initElements(driver,this);
    }

    @Step("Customer is shown Homepage")
    public HomeScreen verifyHomeScreen() {
        commonAction.waitForSomeTime(4000);
        commonAction.waitForElementToBeVisible(destinationAddress);
        String text = destinationAddress.getAttribute("value");
        Assert.assertEquals("Search Drop Location",text,"User is not present on homepage");
        return new HomeScreen(driver);
    }

    public LocationSearch clickSearchLocation() {
        //commonAction.waitForSomeTime(3000);
        commonAction.waitForElementToBeVisible(destinationAddress);
        sourceAddress.click();
        return new LocationSearch(driver);
    }

    public MenuScreen goToMenuScreen() {
        commonAction.waitForElementToBeVisible(profileIcon);
        profileIcon.click();
        return new MenuScreen(driver);
    }
}
