package screens.ride;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonAction;

public class PaymentMode {
    private AppiumDriver driver;
    private CommonAction commonAction;

    public PaymentMode(AppiumDriver driver) {
        commonAction = new CommonAction(driver);
        this. driver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//android.widget.TextView[@text='Cash']")
    private WebElement cash;

    public WebElement getPaymentType(String paymentMode) {
        if (paymentMode.equalsIgnoreCase("Cash"))
            return cash;
        return null;
    }

    public FareEstimate selectPaymentMode(String paymentMode){
        commonAction.waitForSomeTime(3000);
        WebElement selectPayment=getPaymentType(paymentMode);
        commonAction.scrollDownTo(selectPayment);
        selectPayment.click();
        return new FareEstimate(driver);
    }


}
