package screens.menu;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.CommonAction;

public class MyRidesScreen {
    private AppiumDriver driver;
    private CommonAction commonAction;


    public MyRidesScreen(AppiumDriver driver) {
        commonAction = new CommonAction(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Book Rapido\"]")
    private WebElement bookRapido;


    @Step("Verify My Rides List Is Empty")
    public MyRidesScreen verifyMyRidesListIsEmpty(){
        commonAction.waitForSomeTime(2000);
        Assert.assertTrue(commonAction.getElementByXpath("What are you waiting for? Book your first ride with us TODAY!").isDisplayed());
        isBookRapidoAvailable();
        return this;
    }
    public MyRidesScreen isBookRapidoAvailable(){
        bookRapido.click();
        return new MyRidesScreen(driver);

    }
}

