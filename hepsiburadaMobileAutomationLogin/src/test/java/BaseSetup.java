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

String Password_Text ="//android.webkit.WebView[@content-desc=\"Üye Giriş Sayfası & Üye Ol - Hepsiburada\"]/android.view.View[1]/android.view.View[3]/android.view.View[2]/android.widget.EditText";

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
        
        loginAccount();
        NameChange();
    }

    //for login
    public void loginAccount() {
        WebElement account = driver.findElementById("com.pozitron.hepsiburada:id/account_icon");
        account.click();
        WebElement userLogin= driver.findElementById("com.pozitron.hepsiburada:id/llUserAccountLogin");
        userLogin.click();
        WebElement loginEmail = driver.findElementById("txtUserName");
        loginEmail.click();
        loginEmail.sendKeys("username@gmail.com");
        WebElement loginPassword = driver.findElementByXPath(Password_Text);
        loginPassword.click();
        loginPassword.sendKeys("Password");
        WebElement loginButton = driver.findElementById("btnLogin");
        loginButton.click();
        WebElement checkOk = driver.findElementById("android:id/button1");
        checkOk.click(); //tamam butonuna tıklanarak  giriş check edilir.


    }
    //for change name
    public void NameChange() {
        WebElement userAccount = driver.findElementById("com.pozitron.hepsiburada:id/tvUserAccountUsername\n");
        userAccount.click();
        WebElement firstName = driver.findElementById("com.pozitron.hepsiburada:id/etUserFirstName");
        firstName.click();
        firstName.clear();//gülçe
        firstName.sendKeys("isimdegistir");
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(843, 1183));
        action.moveTo(PointOption.point(846, 564));
        action.release();
        action.perform();
        WebElement accountUpdate = driver.findElementById("com.pozitron.hepsiburada:id/btnOkSend");
        accountUpdate.click();
        WebElement checkOk = driver.findElementById("android:id/button1");
        checkOk.click();
        WebElement account = driver.findElementByAccessibilityId("Hesabım");
        account.click();
        MobileElement userAccount2 = driver.findElementById("com.pozitron.hepsiburada:id/tvUserAccountUsername");
        userAccount2.click(); // we can see the name we changed

    }

    @AfterMethod
    public void TearDown() {
        driver.quit();
    }
}
