package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_15_Popup {
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
    }

    @Test
    public void TC_01_Fixed_Popup_In_Dom() {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.cssSelector("button.login_")).click();
        sleepSeconds(2);
        By loginPopup = By.cssSelector("div[id='modal-login-v1'][style]>div");
        //kt login popup đang hiển thị
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#account-input")).sendKeys("Testing");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#password-input")).sendKeys("Testing");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.btn-login-v1")).click();
        sleepSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.error-login-panel")).getText(),"Tài khoản không tồn tại!");
        //close popup
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.close")).click();
        sleepSeconds(2);

        // kt popup k hien thi
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

    }

    @Test
    public void TC_02_Fixed_Popup_In_Dom() {
        driver.get("https://skills.kynaenglish.vn/");
        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleepSeconds(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("div#k-popup-account-login-mb div.modal-content")).isDisplayed());
        System.out.print("Popup k hiển thị " +driver.findElement(By.cssSelector("div#k-popup-account-login-mb div.modal-content")).isDisplayed());

        driver.findElement(By.cssSelector("input#user-login")).sendKeys("testing");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("testing");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        sleepSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
        driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
        sleepSeconds(2);

        Assert.assertFalse(driver.findElement(By.cssSelector("div#k-popup-account-login-mb div.modal-content")).isDisplayed());
        System.out.print("Popup k hiển thị " +driver.findElement(By.cssSelector("div#k-popup-account-login-mb div.modal-content")).isDisplayed());

    }

    @Test
    public void TC_03_Fixed_Popup_Not_In_Dom() {
        driver.get("https://tiki.vn/");
        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container'")).click();
        sleepSeconds(2);
        //kt popup hien thi
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());
        driver.findElement(By.cssSelector("p.login-with-email")).click();
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span[1]")).getText(), "Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='password']/parent::div/following-sibling::span")).getText(), "Mật khẩu không được để trống");
    // đóng close
        driver.findElement(By.cssSelector("img.close-img")).click();
        sleepSeconds(2);
        //khi popup đóng lại thi html k còn trong dom

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content")).size(), 0);

    }
    @Test
    public void TC_04_Fixed_Popup_Not_In_DOM(){
        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).isDisplayed());
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepSeconds(2);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).size(), 0);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public void sleepSeconds(long timeInsecond) {
        try {
            Thread.sleep(timeInsecond *1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
