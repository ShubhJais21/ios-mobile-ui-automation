package userJourney;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import com.rapido.api.utils.MongoDbUtils;
import com.rapido.api.utils.ReporterUtil;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.*;
import utils.CommonAction;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;


public class StartingSteps {

    public AppiumDriver driver;
    private String mobileNumber;
    private MongoDbUtils mongoDbUtils;
    private CommonAction commonAction;

//    String username = System.getenv("LT_USERNAME") == null ? "Subhadip.Sinha" //Enter the Username here
//            : System.getenv("LT_USERNAME");
//    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "slXzMES3RH1XxVfuhlUMGs8ddkz6QGGUB2jHSXjsczrwVOxJDn"  //Enter the Access key here
//            : System.getenv("LT_ACCESS_KEY");

     private String appUrl="";
     private String buildName="";
    public StartingSteps()  {
        mongoDbUtils = new MongoDbUtils();
    }

//    @BeforeSuite(alwaysRun = true)
//    public void uploadApk(ITestContext context){
//        String buildName=String.format("v8 %s Customer build %s",System.getProperty("tag"), Instant.now().toEpochMilli());
//        context.setAttribute("buildName", buildName);
//        if (username==null || accessKey==null || username.isEmpty() || accessKey.isEmpty() ){
//    //        ReporterUtil.log("Username and Access key not found");
//            throw new SkipException("Skipping this exception");
//        }
//
//        String runsOn = System.getProperty("runsOn");
//        if(runsOn.equalsIgnoreCase("lambdatest")) {
//            String url = "https://manual-api.lambdatest.com/app/upload/realDevice";
//            HttpResponse<JsonNode> response = null;
//            try {
//                response = Unirest.post(url)
//                        .basicAuth(username, accessKey)
//                        .field("appFile", new File("./app/customerStaging.apk"))
//                        .field("name", "customer_apk").asJson();
//            } catch (UnirestException e) {
//                e.printStackTrace();
//            }
////            ReporterUtil.log("Response code : " + response.getStatus());
////            ReporterUtil.log("Response body : " + response.getBody());
//            if (response.getStatus() != 200) {
//                System.out.println("Error happened");
//            } else {
//                String appUrl = response.getBody().getObject().get("app_url").toString();
//                context.setAttribute("appUrl", appUrl);
//            }
//
//        }
//        try {
// //           mongoDbUtils.connect();
//        }catch (Exception e){
//  //          ReporterUtil.log("Mongo connection error: "+ e.getMessage());
//        }
//
//    }


//    @BeforeMethod(alwaysRun = true)
//    @Parameters({"Device", "platformVersion", "deviceName"})
//    public void setUp(Method method, @Optional String device, @Optional String platformVersion, @Optional String deviceName,ITestContext context) throws Exception {
//        String runsOn = System.getProperty("runsOn");
//        if(runsOn.equalsIgnoreCase("lambdatest")) {
// //           ReporterUtil.log("=== Using Lambda test capabilities ===");
//            String gridURL = "@mobile-hub.lambdatest.com/wd/hub";
//            String status = "passed";
//            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setCapability("build", context.getAttribute("buildName"));
//            capabilities.setCapability("name", method.getName());
//            capabilities.setCapability("deviceName", deviceName);
//            capabilities.setCapability("platformVersion", platformVersion);
//            capabilities.setCapability("platformName", "android");
//            capabilities.setCapability("isRealMobile", true);
//            //capabilities.setCapability("app","lt://APP10160532421667902097013443"); //Enter the App ID here
//            capabilities.setCapability("app", context.getAttribute("appUrl")); //Enter the App ID here
//            capabilities.setCapability("deviceOrientation", "PORTRAIT");
//            capabilities.setCapability("console", true);
//            capabilities.setCapability("network", false);
//            capabilities.setCapability("visual", true);
//            capabilities.setCapability("autoGrantPermissions", "true");
//            capabilities.setCapability("idleTimeout", "240");
////            ReporterUtil.log("appUrl :"+context.getAttribute("appUrl"));
////            ReporterUtil.log("build Name :"+context.getAttribute("buildName"));
//            try {
//                String hub = "https://" + username + ":" + accessKey + gridURL;
//                driver = new AndroidDriver(new URL(hub), capabilities);
//                //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//            } catch (MalformedURLException e) {
//  //              ReporterUtil.log("Invalid grid URL");
//            } catch (Exception e) {
//  //              ReporterUtil.log(e.getMessage());
//            }
//        }
//        else{
// //           ReporterUtil.log("=== Using local  capabilities ===");
////            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
////            desiredCapabilities.setCapability("automationName", "UiAutomator2");
////            desiredCapabilities.setCapability("platformName", "Android");
////            desiredCapabilities.setCapability("appPackage", "com.rapido.passenger.staging");
////            desiredCapabilities.setCapability("appActivity", "com.rapido.splash.presentation.ui.SplashActivity");
////            desiredCapabilities.setCapability("deviceName", deviceName);
////            desiredCapabilities.setCapability("platformVersion", platformVersion);
////            desiredCapabilities.setCapability("autoGrantPermissions", "true");
////            desiredCapabilities.setCapability("app", new File("app/customerStaging.apk").getAbsolutePath());
////            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
//
//        }
//        commonAction=new CommonAction(driver);
//
//    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"Device", "platformVersion", "deviceName"})
    public void setUp(Method method, @Optional String device, @Optional String platformVersion, @Optional String deviceName)throws Exception
    {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("udid", "6DCBB8FC-7BF0-4CE8-A937-D467DF3491AB");
        capabilities.setCapability("deviceType","SIMULATOR");
        capabilities.setCapability("newCommandTimeout", 3600);
        capabilities.setCapability("includeSafariInWebviews",true);
        capabilities.setCapability("connectHardwareKeyboard",true);
        capabilities.setCapability("nativeApp", true);
        capabilities.setCapability("runsOn", "any");
        capabilities.setCapability("app","/Users/rapido/Documents/Rapido Staging.app");
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities) ;
        try {
            mongoDbUtils.connect();
        }catch (Exception e){
            ReporterUtil.log("Mongo connection error: "+ e.getMessage());
        }
    }


    @AfterSuite(alwaysRun = true)
    public void finalCleanUp(){
        // write code to delete all files matching pattersn *.txt
    }


    public void clearCaptainAndCustomerFile(String file) {
        File file1 = new File(file);
        ReporterUtil.log("its a file " + file1);
        file1.delete();
    }

    @AfterMethod(alwaysRun = true)
    @Step("Deleting the Customer number used")
    public void appClose() {
        try {
            clearCaptainAndCustomerFile(mobileNumber + ".txt");
            ReporterUtil.log("Customer number Deleted");
            driver.quit();
        } catch (Exception e) {
            ReporterUtil.log("File Not Found");
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        try {
            mongoDbUtils.disconnect();
        }catch (Exception e){
            ReporterUtil.log("Mongo disconnection error: "+ e.getMessage());
        }
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
