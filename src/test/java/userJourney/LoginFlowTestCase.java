package userJourney;



//import com.rapido.api.entities.App;
 import com.rapido.api.data.CaptainPool;
 import com.rapido.api.db.DbHelper;
 import com.rapido.api.entities.App;
 import com.rapido.api.utils.CustomerPool;
 import com.rapido.api.utils.DataHelper;
 import io.qameta.allure.Story;
import org.testng.annotations.Test;

import screens.loginandregistration.LoginScreen;
 import screens.loginandregistration.VerifyOtpScreen;
 import utils.Groups;
 import utils.testdata.UserData;

import java.net.MalformedURLException;

public class LoginFlowTestCase extends StartingSteps {

    private CustomerPool customerPool;
    private CaptainPool captainPool;
    private UserData userData;
    VerifyOtpScreen verifyOtpScreen;

    public LoginFlowTestCase() {
        userData = new UserData();
        customerPool = new CustomerPool();
        captainPool = new CaptainPool();
    }

    @Test(groups = {Groups.CUS_SMOKE, Groups.CUS_REGRESSION})
    @Story("Customer getting registered as a new user")
    public void verifyRegistrationFlowWithNewUser() {
        String mobileNumber = customerPool.getNewCustomerWithRandomData().getMobileNumber();
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.getStartedOnStartUp()
        .enterMobileNumberAndSubmit(mobileNumber)
                .validateOTPscreen()
                .enterOTPForNewUser(DbHelper.getOtp(mobileNumber))
                .verifyProfilePageIsDisplayed()
                .enterProfileDetails("Test Automation user",10)
                .verifyHomeScreen()
                .goToMenuScreen()
                .goToProfileSettingsScreen()
                .logout()
                .confirmLogout()
                .verifyUserInLoginScreen();
    }

//    @Test(groups = {Groups.CUS_SMOKE, Groups.CUS_REGRESSION})
//    @Story("Customer getting logged in as an existing user")
//    public void verifyLoginFlowWithExistingUser() {
//        LoginScreen loginScreen = new LoginScreen(driver);
//
//        String mobileNumber = userData.getCustData("alreadyLoggedIn");
//        setMobileNumber(mobileNumber);
//
//        DataHelper.abortExistingOrdersMobileAutomation(mobileNumber, App.CUSTOMER);
//
//        loginScreen.getStartedOnStartUp().enterMobileNumberAndSubmit(mobileNumber)
//                .validateOTPscreen()
//                .enterOTPForExistingUser(DbHelper.getOtp(mobileNumber))
//                .verifyHomeScreen()
//                .goToMenuScreen()
//                .goToProfileSettingsScreen()
//                .logout()
//                .confirmLogout()
//                .verifyUserInLoginScreen();
//    }
//
//    @Test(groups = {Groups.CUS_REGRESSION})
//    @Story("Customer Login with registered user invalid otp and resend otp via sms")
//    public void verifyLoginFlowWithInvalidOtpAndResendOtpViaSms() {
//        LoginScreen loginScreen = new LoginScreen(driver);
//
//        String mobileNumber = customerPool.getNewCustomerWithRandomData().getMobileNumber();
//        setMobileNumber(mobileNumber);
//
//        DataHelper.abortExistingOrdersMobileAutomation(mobileNumber, App.CUSTOMER);
//
//        loginScreen.getStartedOnStartUp().enterMobileNumberAndSubmit(mobileNumber)
//                .validateOTPscreen()
//                .enterInvalidOTP()
//                .verifyInvalidOtpErrorMessageIsShown()
//                .clickSMSResendOtp()
//                .enterOTPForNewUser(DbHelper.getOtp(mobileNumber))
//                .verifyProfilePageIsDisplayed()
//                .enterProfileDetails("test Automation Resend SMS",10)
//                .verifyHomeScreen();
//    }
}
