package screens.ride;

import com.rapido.api.utils.ReporterUtil;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.CommonAction;

public class CaptainSearch {

    private AppiumDriver driver;
    private CommonAction commonAction;

    public CaptainSearch(AppiumDriver driver) {
        commonAction = new CommonAction(driver);
        this. driver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//android.widget.ProgressBar")
    private WebElement captainSearchProgressBar;

    @FindBy(xpath = "//android.widget.TextView[@text='Cancel']")
    private WebElement cancelButton;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[1][@text]")
    private WebElement cancelRideConfimationMessage;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.widget.Button")
    private WebElement doNotCancelButton;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.widget.Button")
    //@FindBy(xpath= "//android.widget.TextView[@text='Don't cancel']")
    private WebElement doNotCancel;

    @FindBy(xpath = "//android.widget.TextView[@text='Cancel Ride']")
    private WebElement cancelRide;

    @FindBy(xpath = "//android.widget.TextView[@text='Cancel trip']")
    private WebElement cancelTrip;

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.TextView[@text]")
    private WebElement cancelRiderButtonText;

    private String cancelReason="//android.widget.TextView[@text='reason']";

    @FindBy(xpath = "//android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[2][@text]")
    private WebElement cancellingFeeWarningMesaage;

    @Step("Verify the Captain Search Screen is shown")
    public CaptainSearch verifyCaptainSearchScreenIsShown(){
        commonAction.waitForSomeTime(3000);
        commonAction.waitForElementToBeVisible(captainSearchProgressBar);
        Assert.assertTrue(captainSearchProgressBar.isDisplayed(),"Progress bar is not displayed");
        Assert.assertTrue(cancelButton.isDisplayed(),"Cancel button is not displayed");
        return this;
    }

    public CaptainSearch donotCancelAndWaitForBit(){
        String cancelFeeText="You might be charged a fee for cancelling this ride";
        String cancelConfirmationTextPopUpText="Are you sure you'd like to cancel this ride?";
        try {
            Assert.assertEquals(cancelRideConfimationMessage.getText(), cancelConfirmationTextPopUpText, "Confirmation text is not same");
            Assert.assertEquals(cancellingFeeWarningMesaage.getText(), cancelFeeText, "Cancelling fee text is not same");
        }catch (Exception e){
            ReporterUtil.log("Error: "+e.getMessage());
        }
        doNotCancel.click();
        return this;
    }


    public CaptainSearch cancelRide(){
        commonAction.waitForSomeTime(2000);
        cancelButton.click();
        commonAction.waitForSomeTime(3000);
        return this;
    }
//    public CaptainSearch cancelTripAfterRideAccepted(){
//        commonAction.waitForSomeTime(10000);
//        commonAction.scrollDownTo(cancelTrip);
//        cancelTrip.click();
//        return this;
//    }

    public FareEstimate confirmCancelRide(){
        commonAction.waitForElementToBeClickable(cancelRide);
        cancelRide.click();
        //commonAction.waitForSomeTime(3000);
        return new FareEstimate(driver);
    }

    public CaptainSearch verifyCancelConfirmationBottomSheetIsShown(){
        String cancelConfirmationText="Are you sure you want to cancel the current order?";
        String cancelButtonText="Cancel Ride";
        Assert.assertTrue(cancelRideConfimationMessage.isDisplayed(),"Cancel confirmation text is not displayed");
        Assert.assertEquals(cancelRideConfimationMessage.getText(),cancelConfirmationText,"Confirmation text is not same");
        Assert.assertEquals(cancelRiderButtonText.getText(),cancelButtonText,"Cancel button text is not matching");

        return this;

    }

}


