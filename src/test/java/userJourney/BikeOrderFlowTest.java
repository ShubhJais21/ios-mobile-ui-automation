package userJourney;

import api.captain.CaptainActions;
//import api.customer.CustomerAction;
import com.rapido.api.customer.entities.Location;
import com.rapido.api.customer.entities.ServiceType;
import com.rapido.api.data.Captain;
import com.rapido.api.data.CaptainPool;
import com.rapido.api.db.DbHelper;
import com.rapido.api.entities.App;
import com.rapido.api.entities.RideLocation;
import com.rapido.api.utils.DataHelper;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import screens.home.HomeScreen;
import screens.loginandregistration.LoginScreen;
import screens.ride.InvoiceAndRating;
import screens.ride.PostOrder;

import utils.Groups;
import utils.testdata.UserData;

public class BikeOrderFlowTest extends StartingSteps{

    private UserData userData;
    private CaptainPool captainPool;
    //private CustomerAction customerAction;


    public BikeOrderFlowTest() {
        userData = new UserData();
        this.captainPool = new CaptainPool();
        //this.customerAction=new CustomerAction();
    }

    @Test(groups = {Groups.CUS_SMOKE,Groups.CUS_REGRESSION})
    @Story("Customer Book Bike Ride")
    public void customerShouldBeAbleToBookAndCompleteRide(){

        Location pickupLocation = RideLocation.COXTOWN.getLocation();
        Location dropLocation = RideLocation.BANNERGATTA_ROAD.getLocation();

        LoginScreen loginScreen = new LoginScreen(driver);

        String mobileNumber = userData.getCustData("bikeOrder");
        setMobileNumber(mobileNumber);

        DataHelper.abortExistingOrdersMobileAutomation(mobileNumber, App.CUSTOMER);

        HomeScreen homeScreen=loginScreen.setCustomerAppCurrentLocation(pickupLocation).getStartedOnStartUp()
                .enterMobileNumberAndSubmit(mobileNumber)
                .enterOTPForExistingUser(DbHelper.getOtp(mobileNumber))
                .verifyHomeScreen();

        // this needs to be pulled from separate csv later
        Captain captain = captainPool.getCaptainForLink_02();
        CaptainActions captainActions = new CaptainActions(captain);
        captainActions.ensureCaptainIsAvailableAtSameLocation(pickupLocation, ServiceType.RAPIDO_TEST);

        homeScreen.clickSearchLocation().userEntersAndSelectPickUpLocation(pickupLocation.getAddress())
                .userEntersAndSelectDropOffLocation(dropLocation.getAddress())
                .userOpenPaymentScreen()
                .selectPaymentMode("Cash")
                .userSelectServiceAsLinkAndBookRide()
                .verifyCaptainSearchScreenIsShown();
        captainActions.acceptRide();

        PostOrder postOrder=new PostOrder(driver);
        String pin=postOrder.getCustomerPin();
        postOrder.verifyPostOrderDetailsDuringCaptainOnTheWay();

        captainActions.arriveAtLocation("");

        postOrder.verifyPostOrderDetailsDuringCaptainArrived();

        captainActions.startRide("",pin);

        postOrder.verifyPostOrderDetailsDuringRideStarted();

        captainActions.completeRide("",dropLocation);

        InvoiceAndRating invoiceAndRating=new InvoiceAndRating(driver);

        invoiceAndRating.selectCashAspayment();

        captainActions.collectCash();

        invoiceAndRating.submitRideFeedback().verifyHomeScreen();
    }
}
