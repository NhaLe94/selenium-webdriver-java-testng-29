package webdriver;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_11_Button_Radio_Checkbox {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {

        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Url() {
        driver.get("https://egov.danang.gov.vn/reg");

        WebElement registerButton = driver.findElement(By.cssSelector("input.egov-button"));
        // verify button disabled khi chua click vào checkbox
        Assert.assertFalse(registerButton.isEnabled());
        driver.findElement(By.cssSelector("input#chinhSach")).click();

        // verify button enabled khi click vào checkbox
        Assert.assertTrue(registerButton.isEnabled());

        // lay ra ma mau nen cua button

        String registerBackgroundColorCode = registerButton.getCssValue("background-color");
        System.out.println("Background Color = "+ registerBackgroundColorCode);
        Color registerBackgroundColor = Color.fromString(registerBackgroundColorCode);
        String registerBackgroundHexa = registerBackgroundColor.asHex();
        System.out.println("Background color hexa = " + registerBackgroundHexa);
        System.out.println("Background color hexa = " + registerBackgroundHexa.toUpperCase());
        System.out.println("Background color hexa = " + registerBackgroundHexa.toLowerCase());
        Assert.assertEquals(registerBackgroundHexa.toLowerCase(), "#ef5a00");

    }

    @Test
    public void TC_02_Fahasa_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));
        Assert.assertFalse(loginButton.isEnabled());
        System.out.println(loginButton.getCssValue("background-color"));

        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(),"#000000");
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("lelucy90@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
        Assert.assertTrue(loginButton.isEnabled());
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(),"#c92127");
    }

    @Test
    public void TC_03_Form() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }


}
