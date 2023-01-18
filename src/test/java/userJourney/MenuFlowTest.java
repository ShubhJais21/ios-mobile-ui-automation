package userjourney;

import com.rapido.api.customer.entities.Location;
import com.rapido.api.db.DbHelper;
import com.rapido.api.entities.RideLocation;
import com.rapido.api.utils.CustomerPool;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import screens.loginandregistration.LoginScreen

import userJourney.StartingSteps;
import utils.Groups;
import utils.testdata.UserData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MenuFlowTest extends StartingSteps {
    private UserData userData;

    public MenuFlowTest() {
        userData = new UserData();
    }

    @Test(groups = {Groups.CUS_REGRESSION})
    @Story("Verify My Ride List When User Has No Order")
    public void verifyMyRideListWhenUserHasNoOrder() {
        LoginScreen loginScreen = new LoginScreen(driver);

        String mobileNumber = userData.getCustData("newlyRegisteredUser");
        setMobileNumber(mobileNumber);

        loginScreen.getStartedOnStartUp().enterMobileNumberAndSubmit(mobileNumber)
                .validateOTPscreen()
                .enterOTPForExistingUser(DbHelper.getOtp(mobileNumber))
                .verifyHomeScreen()
                .goToMenuScreen()
                .goToMyRides()
                .verifyMyRidesListIsEmpty();
    }
}
