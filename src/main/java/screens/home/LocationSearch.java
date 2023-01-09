package screens.home;

import com.rapido.api.utils.ReporterUtil;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import screens.ride.FareEstimate;
import screens.ride.PostOrder;
import utils.CommonAction;

public class LocationSearch {
    private AppiumDriver driver;
    private CommonAction commonAction;

    public LocationSearch(AppiumDriver driver) {
        commonAction = new CommonAction(driver);
        this. driver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "(//XCUIElementTypeApplication[@name=\"Rapido Staging\"]//following::XCUIElementTypeTextField)[2]")
    private WebElement pickupLocation;

    @FindBy(xpath = "(//XCUIElementTypeApplication[@name=\"Rapido Staging\"]//following::XCUIElementTypeTextField)[1]")
    private WebElement dropLocation;

    @FindBy(xpath = "//XCUIElementTypeApplication[@name=\"Rapido Staging\"]//following::XCUIElementTypeTable/XCUIElementTypeCell[1]")
    private WebElement searchResultItems;

    @FindBy(xpath = "(//XCUIElementTypeApplication[@name=\"Rapido Staging\"]//following::XCUIElementTypeTextField)[1]")
    private WebElement editDropLocation;

    @FindBy(xpath = "//android.view.View/android.view.View/android.widget.EditText/android.view.View/android.widget.Button")
    private WebElement clearDropLocation;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Select From Map\"]")
    private WebElement selectOnMap;

    @FindBy(xpath = "//android.view.View/android.widget.EditText")
    private WebElement selectAddressFromMapScreenSearch;

    @FindBy(xpath = "//android.view.View/android.view.View[3]/android.view.View[1]")
    private WebElement searchResultItemsFromMapSreenSearch;


    @Step("enters and select pickUp location")
    public LocationSearch userEntersAndSelectPickUpLocation(String addressText) {
        searchAndSelectPickupLocation(addressText);
        return this;
    }

    @Step("enters and select dropOff location")
    public FareEstimate userEntersAndSelectDropOffLocation(String addressText) {
        searchAndSelectDropLocation(addressText);
        return new FareEstimate(driver);
    }

    @Step("Changed drop location")
    public PostOrder changeDropLocation(String addressText){
        commonAction.waitForElementToBeVisible(editDropLocation);
        clearDropLocation.click();
        editDropLocation.sendKeys(addressText);
        commonAction.hideKeyboard();
        commonAction.waitForElementToBeClickable(searchResultItems);
        searchResultItems.click();
        return new PostOrder(driver);
    }

    @Step("Select pickup location from map")
    public MapsScreen userTapOnSelectOnMapForPickUp(){
        commonAction.waitForElementToBeVisible(pickupLocation);
        pickupLocation.click();
        selectOnMap.click();
        return new MapsScreen(driver);
    }

    @Step("Select drop location from map")
    public MapsScreen userTapOnSelectOnMapForDrop(){
        commonAction.waitForElementToBeVisible(dropLocation);
        dropLocation.click();
        selectOnMap.click();
        return new MapsScreen(driver);
    }

    @Step("User enter and select pickup location from map screen using search")
    public MapsScreen userEnterAndSelectPickupLocationFromMapScreenUsingSearch(String addressText) {
        searchAndSelectAddressFromMapScreenSearch(addressText);
        return new MapsScreen(driver);
    }

    @Step("User enter and select drop location from map screen using search")
    public MapsScreen userEnterAndSelectDropLocationFromMapScreenUsingSearch(String addressText) {
        searchAndSelectAddressFromMapScreenSearch(addressText);
        return new MapsScreen(driver);
    }

    public void searchAndSelectPickupLocation(String addressText){
        commonAction.waitForElementToBeVisible(pickupLocation);
        pickupLocation.click();
        pickupLocation.clear();
        pickupLocation.sendKeys(addressText);
        commonAction.hideKeyboard();
        commonAction.waitForElementToBeClickable(searchResultItems);
        searchResultItems.click();
    }

    public void searchAndSelectDropLocation(String addressText){
        commonAction.waitForElementToBeVisible(dropLocation);
        dropLocation.click();
        dropLocation.clear();
        dropLocation.sendKeys(addressText);
        commonAction.hideKeyboard();
        commonAction.waitForElementToBeClickable(searchResultItems);
        searchResultItems.click();
//        try{
//            searchResultItems.click();
//        }catch (Exception e){
//            ReporterUtil.log("Select location exception: "+ e.getMessage());
//        }
    }

    public void searchAndSelectAddressFromMapScreenSearch(String addressText){
        commonAction.waitForElementToBeVisible(selectAddressFromMapScreenSearch);
        selectAddressFromMapScreenSearch.click();
        selectAddressFromMapScreenSearch.clear();
        selectAddressFromMapScreenSearch.sendKeys(addressText);
        commonAction.hideKeyboard();
        commonAction.waitForElementToBeClickable(searchResultItemsFromMapSreenSearch);
        searchResultItemsFromMapSreenSearch.click();
        try{
            searchResultItemsFromMapSreenSearch.click();
        }catch (Exception e){
            ReporterUtil.log("Select location exception: "+ e.getMessage());
        }
        commonAction.waitForSomeTime(3000);
    }
}
