package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_XPath_Css {

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
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.manage().window().maximize();
    }


    @Test(priority = 0)
    public void Register_01_Empty_Data() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).clear();
        driver.findElement(By.id("txtEmail")).clear();
        driver.findElement(By.id("txtCEmail")).clear();
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtCPassword")).clear();
        driver.findElement(By.id("txtPhone")).clear();

        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

    }

    @Test(priority = 1)
    public void Register_02_Invalid_Email_Address() throws InterruptedException {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("Lucy Le");
        driver.findElement(By.id("txtEmail")).sendKeys("lucyle90@@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("lucyle90@@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("097234112");
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();

        // verify
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");

    }

    @Test(priority = 2)
    public void Register_03_Invalid_Password() throws InterruptedException {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        Thread.sleep(1000);
        driver.findElement(By.id("txtFirstname")).sendKeys("Lucy Le");
        driver.findElement(By.id("txtEmail")).sendKeys("lucyle90@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("lucyle90@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123");
        driver.findElement(By.id("txtCPassword")).sendKeys("123");
        driver.findElement(By.id("txtPhone")).sendKeys("0972341124");
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();

        // verify
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");

    }

    @Test(priority = 3)
    public void Register_04_Invalid_Confirm_Password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("Lucy Le");
        driver.findElement(By.id("txtEmail")).sendKeys("lucyle90@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("lucyle@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("0987666567");
        driver.findElement(By.xpath("//button[text() = 'ĐĂNG KÝ' and @type = 'submit']")).click();

        //verify
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
    }

    @Test(priority = 4)
    public void Register_05_Invalid_Phone_Number() throws InterruptedException {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        Thread.sleep(1000);

        // Phone less than 10 chars
        driver.findElement(By.id("txtFirstname")).sendKeys("Lucy Le");
        driver.findElement(By.id("txtEmail")).sendKeys("lucyle90@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("lucyle90@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("098765432");
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();

        // verify
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

        // Phone more than 11 chars
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("097234112005");
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();

        // verify
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

        // Phone không bắt đầu bằng đầu số các nhà mạng
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("12413132324");
        driver.findElement(By.xpath("//button[text() = 'ĐĂNG KÝ' and @type = 'submit']")).click();
        //verify
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");


    }

    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}
