package screens.ride;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import screens.home.LocationSearch;
import screens.home.MapsScreen;
import utils.CommonAction;

public class EditLocationConfirmScreen {

    private AppiumDriver driver;
    private CommonAction commonAction;

    public EditLocationConfirmScreen(AppiumDriver driver) {
        commonAction = new CommonAction(driver);
        this. driver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[@text]")
    private WebElement editLocationConfirmation;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.TextView[2][@text]")
    private WebElement dropLocationEditTermsAndCondition;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View[2]/android.view.View[2]")
    private WebElement confirmEditDropLocation;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View[2]/android.view.View[1]")
    private WebElement confirmEditPickupLocation;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.widget.TextView[2]")
    private WebElement pickupLocationChangeConditionMessage;


    public EditLocationConfirmScreen varifyConfirmLocationEditScreen(){
        String confirmationScreenText="What do you want to edit?";
        String dropAddressChangeTerms="Can only be edited 3 times";
        commonAction.waitForSomeTime(4000);
        Assert.assertTrue(editLocationConfirmation.getText().contains(confirmationScreenText),"");
        Assert.assertTrue(dropLocationEditTermsAndCondition.getText().contains(dropAddressChangeTerms));
        return this;
    }

    public LocationSearch clickEditDropLocationOnConfirmationScreen(){
        commonAction.waitForElementToBeClickable(confirmEditDropLocation);
        confirmEditDropLocation.click();
        return new LocationSearch(driver);
    }

    public EditLocationConfirmScreen verifyConfirmPickupLocationEditScreen(){
        String confirmationScreenText="What do you want to edit?";
        String expectedPickupAddressChangeConditionMessage="Can only be edited within 200m";
        commonAction.waitForSomeTime(4000);
        Assert.assertTrue(editLocationConfirmation.getText().contains(confirmationScreenText),"");
        Assert.assertTrue(pickupLocationChangeConditionMessage.getText().contains(expectedPickupAddressChangeConditionMessage));
        return this;
    }

    public MapsScreen selectEditPickupLocationOnConfirmationScreen(){
        commonAction.waitForElementToBeClickable(confirmEditPickupLocation);
        confirmEditPickupLocation.click();
        return new MapsScreen(driver);
    }

}
