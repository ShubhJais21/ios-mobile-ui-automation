package utils;

//import com.rapido.api.utils.ReporterUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.remote.HideKeyboardStrategy;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CommonAction {

    private WebDriverWait wait;
    protected AppiumDriver driver;

    public CommonAction(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 20);
    }

    public static String getPropertyValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream inStream = new FileInputStream("utils.testdata.properties");
        properties.load(inStream);
        return properties.getProperty(key);
    }

    public void scrollDownTo(WebElement element) {
        hideKeyboard();
        int i = 0;
        while (i < 12) {
            try {
                if (element.isDisplayed()){
                    return;
                }
            } catch (Exception e) {

            }
            scrollDown();
            waitForSomeTime(1000);
            i++;
        }
        Assert.fail("Did not find : " + element.toString());
    }

    public void scrollDown() {
        Dimension dimension=driver.manage().window().getSize();
        int height= dimension.getHeight();
        int width=dimension.getWidth();

        int startX=width/2;
        int endX=width/2;
        int startY=(int)(height*.70);
        int endY=(int)(height*.10);

        TouchAction touchAction=new TouchAction(driver);
        touchAction.press(startX,startY).moveTo(endX,endY).release().perform();
    }
    public void scrollToEnd() {
        driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(20)"));
    }


    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBeVisible(WebElement element, int timeout) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void hideKeyboard() {
        try {
            driver.findElementByXPath(String.format("//XCUIElementTypeButton[@name='%s']", "Done")).click();
        } catch (WebDriverException e) {
  //          ReporterUtil.log("Exception in hiding keyboard: "+e.getMessage());
        }
    }

    public void waitForSomeTime(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
  //          ReporterUtil.log("Exception in waiting: "+e.getMessage());
        }
    }

    public void waitForTextToBePresentInElement(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void scrollAndRelease() {
        Dimension dimension=driver.manage().window().getSize();
        int height= dimension.getHeight();
        int width=dimension.getWidth();

        int startX=width/2;
        int endX=width/2;
        int startY=(int)(height*.50);
        int endY=(int)(height*.30);

        TouchAction touchAction=new TouchAction(driver);
        touchAction.press(startX,startY).moveTo(endX,endY).release().perform();
    }

    public String generateRandomEmail(int length) {

        String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890";
        String email = "";
        String temp = RandomStringUtils.random(length, allowedChars);
        email = temp.substring(0, temp.length() - 9) + "@yopmail.com";
        return email;
    }


}
