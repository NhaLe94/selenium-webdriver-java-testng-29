package testcase;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.RegisterPage;

import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class Register {
    WebDriver driver;
    public RegisterPage registerPage ;
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
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.manage().window().maximize();
    }

    @Test
    public void Register_01_Empty_Data(){
        registerPage = new RegisterPage(driver);

        registerPage.clickSignUp();

        // Verify

    }

    @Test
    public void Register_02_Invalid_Email_Address(){
        registerPage = new RegisterPage(driver);

        registerPage.enterFirstName("Lucy Le");
        registerPage.enterEmail("lucyle90@@gmail.com");
        registerPage.enterConfirmEmail("lucyle90@gmail.com");
        registerPage.enterPassword("123456");
        registerPage.enterConfirmPassword("123456");
        registerPage.enterPhone("0987654321");
        registerPage.clickSignUp();
        // Verify
        Assert.assertTrue(registerPage.verifyErrorEmail().contains("Vui lòng nhập email hợp lệ"));
        Assert.assertTrue(registerPage.verifyErrorEmail().contains("Email nhập lại không đúng"));


    }

}
