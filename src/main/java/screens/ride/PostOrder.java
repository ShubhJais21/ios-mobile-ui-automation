package screens.ride;

import com.rapido.api.utils.ReporterUtil;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import screens.home.HomeScreen;
import screens.home.LocationSearch;
import screens.home.MapsScreen;
import utils.CommonAction;

public class PostOrder {
    private AppiumDriver driver;
    private CommonAction commonAction;

    @FindBy(xpath = "//android.widget.TextView[@text='Start ride with PIN']")
    private WebElement pinText;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View[2]/android.view.View[3]/android.view.View[1]/android.widget.TextView")
    private WebElement pinvalue;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View[2]/android.view.View[4]/android.widget.TextView[4]")
    private WebElement captainRating;

    @FindBy(xpath="//android.view.View/android.view.View/android.view.View[2]/android.view.View[4]/android.widget.TextView[3]")
    private WebElement captainName;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View[2]/android.view.View[4]/android.widget.TextView[1]")
    private WebElement captainVehicleNumber;

    @FindBy(xpath = "//android.view.View/android.view.View[2]/android.view.View[4]/android.view.View/android.widget.Button")
    private WebElement callButton;

    @FindBy(xpath = "//android.widget.TextView[@text='Safety']")
    private WebElement safetyButton;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.TextView[1][@text  ]")
    private WebElement topPickUpLocationBox;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.TextView[2][@text]")
    private WebElement topDropLocationBox;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.widget.Button")
    private WebElement topLocationEditButton;

    @FindBy(xpath = "//android.widget.TextView[contains(text(),'Paying via']")
    private WebElement payViaMode;

    @FindBy(xpath = "//android.widget.TextView[@text='Your ride is insured']")
    private WebElement rideIsInsured;

    @FindBy(xpath = "//android.widget.TextView[@text='To pay']")
    private WebElement toPayText;

    //@FindBy(xpath = "//android.view.View/android.view.View/android.view.View[2]/android.view.View[4]/android.widget.TextView[6]")
    @FindBy(xpath = "//android.widget.TextView[contains(text(),'₹']")
    private WebElement totalPayAmount;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]")
    private WebElement captainETATimmer;

    @FindBy(xpath = "//android.widget.TextView[contains(text(),'Message Captain')]")
    private WebElement captainMessagePlaceHolder;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.widget.TextView[6][@text]")
    private WebElement pickupLocationAddressText;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.widget.Button[1]")
    private WebElement pickUpEditButton;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.widget.TextView[8][@text]")
    private WebElement dropLocationAddress;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.widget.Button[2]")
    private WebElement dropEditFromButton;

    @FindBy(xpath = "//android.widget.TextView[@text='Cancel trip']")
    private WebElement cancelTrip;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[1][@text]")
    private WebElement captainEtaTimerText;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.TextView[2][@text]")
    private WebElement captainArriveEtaTimerText;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[1][@text]")
    private WebElement riderStatusText;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View/android.view.View/android.widget.TextView")
    private WebElement riderStatusDuringRide;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[1][@text]")
    private WebElement dropLocationAddressTextDuringRide;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View[2]/android.view.View[3]/android.widget.TextView[4]")
    private WebElement totalPayAmountDuringRide;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View[2]/android.view.View[3]/android.widget.TextView[5]")
    private WebElement payViaModeDuringRide;

    @FindBy(xpath = "//android.widget.Toast[1]")
    private WebElement cancelRideToastMessage;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.widget.Button")
    private WebElement editDropDuringRider;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.widget.Button")
    private WebElement editLocationButtonOnTop;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.TextView[2][@text]")
    private WebElement getDropAddressFromHeaderLocationBarBeforeRide;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.TextView[@text]")
    private WebElement getDropAddressFromHeaderlocationBarAfterRide;

    private String cancelReason="//android.widget.TextView[@text='reason']";

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.Button")
    private WebElement cancelRide;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[1][@text]")
    private WebElement riderStatusAfterArrive;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.TextView[1][@text]")
    private WebElement pickUpAddressFromHeaderLocationBarBeforeRide;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[4]/android.widget.Button[1]")
    private WebElement editPickUpFromBottom;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[4]/android.widget.TextView[6][@text]")
    private WebElement pickUpLocationAddressFromButtomSection;



    public PostOrder(AppiumDriver driver) {
        commonAction = new CommonAction(driver);
        this. driver= driver;
        PageFactory.initElements(driver,this);
    }

    public boolean getRideStatusText(String rideStatusText){
        return driver.findElementByXPath("//android.widget.TextView[contains(text(),"+rideStatusText+")]").isDisplayed();
    }

    public String getCustomerPinElementByIndex(String index){
        return driver.findElementByXPath("//android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View["+index+"]/android.widget.TextView[@text]").getText();
    }
    public String getCustomerPin(){
        String pin="";
        commonAction.waitForSomeTime(10000);
        for(int i=1;i<=4;i++){
            pin=pin.concat(getCustomerPinElementByIndex(String.valueOf(i)));
        }
        return pin;
    }

    @Step("Verify Post Order Details During Captain OnTheWay")
    public PostOrder verifyPostOrderDetailsDuringCaptainOnTheWay(){
        commonAction.waitForSomeTime(10000);

        verifyRiderStatusIsDisplayed("way to pickup","min");
        verifySafetyIsDisplayed();
        verifyCaptainDetailsIsDisplayed();
        verifyCallAndChatIsDisplayed();
        commonAction.scrollDownTo(dropEditFromButton);
        verifyPickupAndDropAddressIsDisplayed();
        commonAction.scrollDownTo(rideIsInsured);
        verifyPaymentTypeAndInsuranceIsDisplayed();

        return this;
    }

    @Step("Verify Post Order Details During Captain Arrived")
    public PostOrder verifyPostOrderDetailsDuringCaptainArrived(){
        commonAction.waitForSomeTime(10000);

        verifySafetyIsDisplayed();
        verifyRiderStatusIsDisplayed("has arrived!","min");
        verifyCaptainDetailsIsDisplayed();
        verifyCallAndChatIsDisplayed();
        // commonAction.scrollUpTo(pickUpEditButton);
        verifyPickupAndDropAddressIsDisplayed();
        // commonAction.scrollDownTo(rideIsInsured);
        verifyPaymentTypeAndInsuranceIsDisplayed();

        return this;
    }

    @Step("Verify Post Order Details During Ride Started")
    public InvoiceAndRating verifyPostOrderDetailsDuringRideStarted(){
        commonAction.waitForSomeTime(10000);
        verifyRiderStatusIsDisplayed("Reaching","min");
        verifySafetyIsDisplayed();
        verifyCaptainDetailsIsDisplayed();
        verifyDropAddressAfterRiderStartIsDisplayed();
        verifyPaymentTypeAndInsuranceIsDisplayed();

        return new InvoiceAndRating(driver);
    }

    public PostOrder cancelTripAfterRideAccepted(){
        // commonAction.waitForSomeTime(10000);
        commonAction.scrollDownTo(cancelTrip);
        cancelTrip.click();
        return this;
    }

    public PostOrder scrollDown(){
        commonAction.scrollDownTo(dropEditFromButton);
        return this;
    }
    @Step("Click on edit drop location before rider start")
    public LocationSearch tapOnEditDropLocationBeforeRideStart(){
        commonAction.waitForElementToBeClickable(dropEditFromButton);
        dropEditFromButton.click();
        return new LocationSearch(driver);
    }

    @Step("Click on edit drop location after ride start")
    public LocationSearch tapOnEditDropLocationAfterRideStart(){
        commonAction.waitForElementToBeClickable(editDropDuringRider);
        editDropDuringRider.click();
        return new LocationSearch(driver);
    }

    @Step("Verify changed drop address before ride start")
    public PostOrder verifyChangedDropAddressBeforeRideStart(String expectedAddress){
        commonAction.waitForSomeTime(7000);
        commonAction.waitForElementToBeVisible(dropLocationAddress);
        Assert.assertTrue(dropLocationAddress.getText().contains(expectedAddress),String.format("Expected Drop location is %s but found %s",expectedAddress,dropLocationAddress.getText()));
        return this;
    }

    @Step("Verify changed drop location after ride started")
    public PostOrder verifyChangedDropAddressAfterRideStart(String expectedAddress){
        commonAction.waitForSomeTime(7000);
        commonAction.waitForElementToBeVisible(dropLocationAddressTextDuringRide);
        String actualDropAddressText=dropLocationAddressTextDuringRide.getText();
        Assert.assertTrue(actualDropAddressText.contains(expectedAddress),String.format("Expected Drop location is %s but found %s",expectedAddress,actualDropAddressText));
        return this;
    }

    @Step("Click on edit button from top of the order screen")
    public EditLocationConfirmScreen tapHeaderLocationEditButton(){
        commonAction.waitForElementToBeVisible(editLocationButtonOnTop);
        editLocationButtonOnTop.click();
        return new EditLocationConfirmScreen(driver);
    }

    @Step("Verify change drop address on header section before the ride start")
    public PostOrder verifyChangeDropAddressOnHeaderSectionBeforeTheRideStart(String expectedAddress){
        commonAction.waitForSomeTime(7000);
        commonAction.waitForElementToBeVisible(getDropAddressFromHeaderLocationBarBeforeRide);
        String actualDropAddressText=getDropAddressFromHeaderLocationBarBeforeRide.getText();
        Assert.assertTrue(actualDropAddressText.contains(expectedAddress),String.format("Expected Drop location is %s but found %s",expectedAddress,actualDropAddressText));
        return this;
    }

    @Step("Verify change drop address on header section after the ride start")
    public PostOrder verifyChangeDropAddressOnHeaderScetionAfterTheRiderStart(String expectedAddress){
        commonAction.waitForSomeTime(7000);
        commonAction.waitForElementToBeVisible(getDropAddressFromHeaderlocationBarAfterRide);
        String actualDropAddressText=getDropAddressFromHeaderlocationBarAfterRide.getText();
        Assert.assertTrue(actualDropAddressText.contains(expectedAddress),String.format("Expected Drop location is %s but found %s",expectedAddress,actualDropAddressText));
        return this;
    }

    @Step("Click on edit drop address on header location bar section")
    public LocationSearch clickEditDropAddressOnHeaderLocationBar(){
        commonAction.waitForElementToBeVisible(editLocationButtonOnTop);
        editLocationButtonOnTop.click();
        return new LocationSearch(driver);
    }

    @Step("Verify captain status is displayed or not")
    public void verifyRiderStatusIsDisplayed(String statusText, String timerText){
        try {
            Assert.assertTrue(getRideStatusText(statusText), "Captain status is not displayed");
            //Assert.assertTrue(captainEtaTimerText.getText().contains(timerText),"Timer text is not displayed");
        }catch (Exception e){
            ReporterUtil.log("Exception occured during captain status verification:  "+e.getMessage());
        }
    }

    @Step("Verify safety button is displayed or not")
    public void verifySafetyIsDisplayed(){
        Assert.assertTrue(safetyButton.isDisplayed(),"Safety button is not displayed");
    }

    @Step("Verify captain details are displayed or not")
    public void verifyCaptainDetailsIsDisplayed(){
        try {
            Assert.assertTrue(captainName.isDisplayed(), "Captain name is not displayed");
            Assert.assertTrue(pinText.isDisplayed(), "Pin is not displayed");
            Assert.assertTrue(captainRating.isDisplayed(), "Captain rating is not displayed");
            Assert.assertTrue(captainVehicleNumber.isDisplayed(), "Captain vehicle is not displayed");
        }catch (Exception e){
            ReporterUtil.log("Exception occured during captain details verification:  "+e.getMessage());
        }
    }

    @Step("Verify call and chat is displayed or not")
    public void verifyCallAndChatIsDisplayed(){
        try {
            Assert.assertTrue(callButton.isDisplayed(), "Call button is not displayed");
            Assert.assertTrue(captainMessagePlaceHolder.isDisplayed(), "Message place holder is not displayed");
        }catch (Exception e){
            ReporterUtil.log("Exception occured during call and chat details verification:  "+e.getMessage());
        }
    }

    @Step("Verify pickup and drop is displayed or not")
    public void verifyPickupAndDropAddressIsDisplayed(){
        try {
            Assert.assertTrue(pickupLocationAddressText.isDisplayed(), "Pickup location is not displayed");
            Assert.assertTrue(dropLocationAddress.isDisplayed(), "Drop location is not displayed");
        }catch (Exception e){
            ReporterUtil.log("Exception occured during pickup and drop details verification:  "+e.getMessage());
        }
    }

    @Step("Verify payment type and insurance is displayed or not")
    public void verifyPaymentTypeAndInsuranceIsDisplayed(){
        try {
            Assert.assertTrue(toPayText.isDisplayed(), "To pay is not displayed");
            //Assert.assertTrue(totalPayAmount.isDisplayed(), "Total amount is not displayed");
            Assert.assertTrue(rideIsInsured.isDisplayed(), "Insurance is not displayed");
        }catch(Exception e){
            ReporterUtil.log("Exception occured during payemt and insurance details verification:  "+e.getMessage());
        }
    }

    @Step("Verify drop address after ride start is displayed or not")
    public void verifyDropAddressAfterRiderStartIsDisplayed(){
        try {
            Assert.assertTrue(dropLocationAddressTextDuringRide.isDisplayed(), "Drop location is not displayed");
        }catch (Exception e){
            ReporterUtil.log("Exception occured during ride drop address details verification:  "+e.getMessage());
        }
    }
    public PostOrder selectCancelRideReason(String Reason){
        String cancelreason=  cancelReason.replace("reason",Reason);
        commonAction.waitForSomeTime(3000);
        WebElement reason= driver.findElementByXPath(cancelreason);
        commonAction.waitForElementToBeVisible(reason);
        reason.click();
        return this ;
    }

    public HomeScreen confirmCancelRideOnPostOrder(){
        commonAction.waitForElementToBeClickable(cancelRide);
        cancelRide.click();
        commonAction.waitForSomeTime(3000);
        return new HomeScreen(driver);
    }

    public String getCurrentPickupAddressFromHeaderSection(){
        return pickUpAddressFromHeaderLocationBarBeforeRide.getText();
    }

    public PostOrder verifyEditedPickupAddressFromHeader(String addressBeforeEdit ) {
        commonAction.waitForSomeTime(4000);
        String addressAfterEdit= getCurrentPickupAddressFromHeaderSection();
        Assert.assertNotEquals(addressBeforeEdit,addressAfterEdit,"Edit pickup location is not working");
        return this;
    }

    public PostOrder verifyEditedPickupAddressFromHeaderAfterArrived(String expectedAddress) {
        commonAction.waitForSomeTime(4000);
        String addressAfterEdit= getCurrentPickupAddressFromHeaderSection();
        Assert.assertEquals(expectedAddress,addressAfterEdit,"Edit pickup location is not working after captain arrived");
        return this;
    }

    public String getCurrentPickupAddressFromBottomSection(){
        return pickUpLocationAddressFromButtomSection.getText();
    }

    @Step("Click on edit pickup location")
    public MapsScreen tapOnEditPickupLocationIcon(){
        commonAction.waitForElementToBeClickable(editPickUpFromBottom);
        editPickUpFromBottom.click();
        return new MapsScreen(driver);
    }

    public PostOrder verifyEditedPickupAddressFromBottom(String addressBeforeEdit ) {
        commonAction.waitForSomeTime(4000);
        String addressAfterEdit= getCurrentPickupAddressFromBottomSection();
        Assert.assertNotEquals(addressBeforeEdit,addressAfterEdit,"Edit pickup location is not working");
        return this;
    }

    public PostOrder verifyEditedPickupAddressFromBottomAfterArrived(String expectedAddress) {
        commonAction.waitForSomeTime(4000);
        String addressAfterEdit= getCurrentPickupAddressFromBottomSection();
        Assert.assertTrue(addressAfterEdit.contains(expectedAddress),"Edit pickup location is not working after captain arrived");
        return this;
    }
}
