package screens.home;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import screens.ride.FareEstimate;
import screens.ride.PostOrder;
import utils.CommonAction;

public class MapsScreen {
    private AppiumDriver driver;
    private CommonAction commonAction;


    public MapsScreen(AppiumDriver driver) {
        this.driver = driver;
        commonAction = new CommonAction(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//android.widget.TextView[@text='Confirm Pickup']")
    private WebElement confirmPickup;

    @FindBy(xpath = "//android.view.View/android.view.View[2]/android.view.View[2]/android.widget.TextView")
    private WebElement editPickUpLocationCondition;

    @FindBy(xpath = "//android.view.View/android.view.View[2]/android.widget.TextView[2]")
    private WebElement currentLocationOnMap;

    @FindBy(xpath = "//android.widget.TextView[@text='Pickup Point']")
    private WebElement pickUpPoint;

    private String afterChangePickUpPoint;

    @FindBy(xpath = "//android.widget.TextView[@text='Drop Point']")
    private WebElement dropPoint;

    @FindBy(xpath = "//android.widget.TextView[@text='Search']")
    private WebElement search;

    @FindBy(xpath = "//android.widget.TextView[@text='Confirm Drop']")
    private WebElement confirmDrop;

    @FindBy(xpath = "//android.view.View/android.widget.TextView[3]")
    private WebElement dropLocationOnMap;


    @Step("Change the pickup location")
    public MapsScreen changePickupocationFromMap(){
        //checkPickUpPoint();
        String befireChangePickUpPoint=getCustomerAddress();
        pickUpLocationAddressChangeCondition();
        //commonAction.waitForSomeTime(3000);
        commonAction.scrollAndRelease();
        commonAction.waitForSomeTime(3000);
        String afterChangePickUpPoint=getCustomerAddress();
        Assert.assertNotEquals(befireChangePickUpPoint,afterChangePickUpPoint,"Both pickup location are same");
        return this;
    }

    @Step("Verify Map Pickup screen")
    public MapsScreen verifyPickUpScreen(){
        commonAction.waitForElementToBeVisible(pickUpPoint);
        Assert.assertTrue(pickUpPoint.isDisplayed());
        Assert.assertTrue(search.isDisplayed());
        //Assert.assertTrue(currentLocationOnMap.isDisplayed());
        Assert.assertTrue(confirmPickup.isDisplayed());
        return this;
    }

    public String getCustomerAddress(){
        commonAction.waitForElementToBeVisible(currentLocationOnMap);
        Assert.assertTrue(currentLocationOnMap.isDisplayed());
        String address=currentLocationOnMap.getText();
        return address;
    }
    public void pickUpLocationAddressChangeCondition(){
        commonAction.waitForElementToBeVisible(editPickUpLocationCondition);
        Assert.assertEquals(editPickUpLocationCondition.getText(),"Change upto 200m without any fee","Message is not correct");
    }

    @Step("Confirm pickup location from map")
    public PostOrder confirmPickupAfterEdit(){
        commonAction.waitForElementToBeVisible(confirmPickup);
        confirmPickup.click();
        return new PostOrder(driver);
    }

    @Step("Select location using scroll")
    public MapsScreen selecLocationUsingScrollFromMap(){
        commonAction.scrollAndRelease();
        commonAction.waitForSomeTime(3000);
        return this;
    }

    @Step("Confirm pickup location from map")
    public LocationSearch confirmPickup(){
        commonAction.waitForElementToBeVisible(confirmPickup);
        confirmPickup.click();
        return new LocationSearch(driver);
    }

//    @Step("Select the drop location")
//    public MapsScreen selectDropLocationFromMap(){
//        commonAction.scrollAndRelease();
//        commonAction.waitForSomeTime(3000);
//        return this;
//    }

    @Step("Verify Map drop screen")
    public MapsScreen verifyDropscreen(){
        commonAction.waitForElementToBeVisible(dropPoint);
        Assert.assertTrue(dropPoint.isDisplayed());
        Assert.assertTrue(search.isDisplayed());
        //Assert.assertTrue(dropLocationOnMap.isDisplayed());
        Assert.assertTrue(confirmDrop.isDisplayed());
        return this;
    }

    @Step("Confirm drop location from map")
    public FareEstimate confirmDrop(){
        commonAction.waitForElementToBeVisible(confirmDrop);
        confirmDrop.click();
        return new FareEstimate(driver);
    }

    @Step("Click on search from the map screen")
    public LocationSearch tapOnSearchButtonOnMapScreen(){
        search.click();
        return new LocationSearch(driver);
    }

}
