import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class MobileWebTest {

    AppiumDriverLocalService service;
    private String email = "username";
    private String password = "password";
//    WebDriverWait wait;

    @Before
    public void before() {

        String IP = "0.0.0.0";
        int port = 4723;
        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .withIPAddress(IP)
                .usingPort(port));
        service.start();
    }

    @After
    public void after() {
        service.stop();
    }

    @Test
    public void testOpenAliExpress() throws Exception {

        System.out.println("Step 1. Create new driver");
        AppiumDriver driver = getAppiumDriver("Android", new URL("http://127.0.0.1:4723/wd/hub"));
        WebDriverWait wait = new WebDriverWait(driver, 30);

        System.out.println("Step 2. Open website");
        driver.get("https://facebook.com");

        System.out.println("Step 3. Enter email");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        driver.findElement(By.name("email")).sendKeys("max@test.com");

        System.out.println("Step 4. Enter password");
        driver.findElement(By.name("pass")).sendKeys("123456");

        System.out.println("Step 5. Click Login button");
        driver.findElement(By.name("login")).click();

        System.out.println("Step 6. Check error message");
        Thread.sleep(5000);
        assertTrue(driver.findElement(By.cssSelector("div[data-sigil='m_login_notice']")).getText().contains("You've tried"));

        System.out.println("Step 7. Close driver");
        driver.quit();
    }

    @Test
    public void aliCoins() throws Exception {

            System.out.println("Step 1. Create new driver");
            AppiumDriver driver = null;

            driver = getAppiumDriver("Android", new URL("http://127.0.0.1:4723/wd/hub"));


        WebDriverWait wait = new WebDriverWait(driver, 30);
            Thread.sleep(10000);

                WebElement button = driver.findElement(By.xpath("//*[contains(@resource-id, 'recyclerView')]/android.widget.FrameLayout[3]"));
                Thread.sleep(3000);
                button.click();
                Thread.sleep(6000);

                WebElement buttonCoins = driver.findElement(By.xpath("//*[contains(@content-desc, '+ ')]"));
                Thread.sleep(2000);
                buttonCoins.click();
                Thread.sleep(6000);

                WebElement buttonBtnSignIn = driver.findElement(By.xpath("//*[contains(@resource-id, 'btn_sign_in')]"));
                Thread.sleep(2000);
                buttonBtnSignIn.click();
                Thread.sleep(3000);

                WebElement fieldEmail = driver.findElement(By.xpath("//*[contains(@resource-id, 'et_email')]"));
                Thread.sleep(2000);
                fieldEmail.sendKeys(email);
                Thread.sleep(1000);

                WebElement fieldPassword = driver.findElement(By.xpath("//*[contains(@resource-id, 'et_password')]"));
                Thread.sleep(2000);
                fieldPassword.sendKeys(password);
                Thread.sleep(1000);

                WebElement btnBtnSignIn = driver.findElement(By.xpath("//*[contains(@resource-id, 'tv_signin_btn_label')]"));
                Thread.sleep(2000);
                btnBtnSignIn.click();
                Thread.sleep(3000);

                WebElement buttonBackArrow = driver.findElement(By.xpath("//*[contains(@content-desc, 'Navigate up')]"));
                Thread.sleep(2000);
                buttonBackArrow.click();
                Thread.sleep(3000);

                WebElement buttonBackArrowCoins = driver.findElement(By.xpath("//*[contains(@content-desc, 'Coins & Coupons')]/..//*[1]"));
                Thread.sleep(2000);
                buttonBackArrowCoins.click();
                Thread.sleep(3000);

                button.click();
                Thread.sleep(6000);

                WebElement buttonCoins16 = driver.findElement(By.xpath("//*[contains(@content-desc, '+ 16')]"));
                Thread.sleep(2000);
                buttonCoins16.click();
                Thread.sleep(3000);

//                WebElement buttonLF = driver.findElement(By.xpath("//*[contains(@content-desc, 'Lucky Forest')]"));
//                Thread.sleep(6000);
//            buttonLF.click();

//            Thread.sleep(10000);


    }

    private AppiumDriver getAppiumDriver(String platform, URL serverUrl) {
        return platform.equals("iOS") ? getIOSDriver(serverUrl) : getAndroidDriver(serverUrl);
    }

    private AppiumDriver getIOSDriver(URL serverUrl) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.3");

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
        capabilities.setCapability(MobileCapabilityType.UDID, "F97C79FE-5F55-4CE3-BA88-9351B7A9421F");

        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, "true");

        capabilities.setCapability("showXcodeLog", "true");
        capabilities.setCapability("autoAcceptAlerts", "true");

        return new IOSDriver(serverUrl, capabilities);
    }

    private AppiumDriver getAndroidDriver(URL serverUrl) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("platformName", "ANDROID");
//        capabilities.setCapability(CapabilityType.PLATFORM_NAME, "ANDROID");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android SDK built for x86");
        capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
//        capabilities.setCapability(MobileCapabilityType.APP, "C:\\Users\\amele\\Downloads\\programs\\com.alibaba.aliexpresshd_apps.evozi.com.apk");
        capabilities.setCapability("appPackage", "com.alibaba.aliexpresshd");
        capabilities.setCapability("appActivity", "com.alibaba.aliexpresshd.home.ui.MainActivity");
        capabilities.setCapability("automationName", "UiAutomator1");
//        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
//        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");

        return new AndroidDriver(serverUrl, capabilities);
    }

}
