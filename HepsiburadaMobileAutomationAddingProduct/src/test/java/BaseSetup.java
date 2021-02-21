import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseSetup {
    String Product ="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.ImageView";

    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;
    @BeforeMethod

    public void setup () throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "cihaz3");
        caps.setCapability("udid", "emulator-5554");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "9.0");
        caps.setCapability("appPackage", "com.pozitron.hepsiburada");
        caps.setCapability("appActivity","com.hepsiburada.ui.startup.SplashActivity");
        caps.setCapability("noReset","false");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void loginTest () throws InterruptedException {

        AddtoProduct();

    }

    public void AddtoProduct() {
        WebElement appOpen = driver.findElementByAccessibilityId("Hepsiburada");
        appOpen.click();
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(1278, 1473));
        action.moveTo(PointOption.point(986, 765));
        action.release();
        action.perform();
        WebElement superPrice = driver.findElementById("com.pozitron.hepsiburada:id/dod_all");
        superPrice.click();
        WebElement productChoose = driver.findElementByXPath(Product);
        productChoose.click();
        WebElement productImage= (MobileElement) driver.findElementById("com.pozitron.hepsiburada:id/productImage");
        productImage.click();
        TouchAction action2 = new TouchAction(driver);
        action2.press(PointOption.point(993, 1140));
        action2.moveTo(PointOption.point(87, 1140));
        action2.release();
        action2.perform();
        MobileElement addtoBasket = driver.findElementById("com.pozitron.hepsiburada:id/product_detail_add_to_cart_text");
        addtoBasket.click();
        MobileElement goBasket =  driver.findElementById("com.pozitron.hepsiburada:id/goBasketTxt");
        goBasket.click();

    }

    @AfterMethod

    public void TearDown() {
        driver.quit();
    }

}
