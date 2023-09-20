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
    public static RegisterPage registerPage;

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
    public void Register_02_Invalid_Email_Address() {
       // registerPage = new RegisterPage(driver);

        registerPage.enterFirstName("Lucy Le");
        registerPage.enterEmail("lucyle90@@gmail.com");
        registerPage.enterConfirmEmail("lucyle90@@@gmail.com");
        registerPage.enterPassword("123456");
        registerPage.enterConfirmPassword("123456");
        registerPage.enterPhone("0987654321");
        registerPage.clickSignUp();
        // Verify
        Assert.assertTrue(registerPage.verifyErrorEmail().contains("Vui lòng nhập email hợp lệ"));
        Assert.assertTrue(registerPage.verifyErrorCEmail().contains("Email nhập lại không đúng"));

    }

    @Test
    public void Register_03_Invalid_Confirm_Email_Address() {
        registerPage = new RegisterPage(driver);

        registerPage.enterFirstName("Lucy Le");
        registerPage.enterEmail("lucyle90@gmail.com");
        registerPage.enterConfirmEmail("lucyle90@@gmail.com");
        registerPage.enterPassword("123456");
        registerPage.enterConfirmPassword("123456");
        registerPage.enterPhone("0987654321");
        registerPage.clickSignUp();
        // Verify
        Assert.assertTrue(registerPage.verifyErrorCEmail().contains("Email nhập lại không đúng"));

    }

    @Test
    public void Register_04_Invalid_Password() {
        registerPage = new RegisterPage(driver);

        registerPage.enterFirstName("Lucy Le");
        registerPage.enterEmail("lucyle90@gmail.com");
        registerPage.enterConfirmEmail("lucyle90@gmail.com");
        registerPage.enterPassword("123");
        registerPage.enterConfirmPassword("123");
        registerPage.enterPhone("0987654321");
        registerPage.clickSignUp();
        // Verify
        Assert.assertTrue(registerPage.verifyErrorPassword().contains("Mật khẩu phải có ít nhất 6 ký tự"));

    }

    @Test
    public void Register_05_Invalid_Confirm_Password() {
        registerPage = new RegisterPage(driver);

        registerPage.enterFirstName("Lucy Le");
        registerPage.enterEmail("lucyle90@gmail.com");
        registerPage.enterConfirmEmail("lucyle90@gmail.com");
        registerPage.enterPassword("123456");
        registerPage.enterConfirmPassword("12345678");
        registerPage.enterPhone("0987654321");
        registerPage.clickSignUp();
        // Verify
        Assert.assertTrue(registerPage.verifyErrorCPassword().contains("Mật khẩu bạn nhập không khớp"));

    }

    @Test
    public void Register_06_Invalid_PhoneNumber() {
        registerPage = new RegisterPage(driver);

        registerPage.enterFirstName("Lucy Le");
        registerPage.enterEmail("lucyle90@gmail.com");
        registerPage.enterConfirmEmail("lucyle90@gmail.com");
        registerPage.enterPassword("123456");
        registerPage.enterConfirmPassword("12345678");
        registerPage.enterPhone("098765432");
        registerPage.clickSignUp();
        // Verify phone number less than 10 chars
        Assert.assertTrue(registerPage.verifyErrorPhone().contains("Số điện thoại phải từ 10-11 số."));
        // Verify phone number more  than 11 chars




    }
}
