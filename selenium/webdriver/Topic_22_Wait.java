package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_22_Wait {
    WebDriver driver;
    WebDriverWait explicitWait;
    By reconfirmEmailTextBox = By.cssSelector("input[name='reg_email_confirmation__']");
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
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Visible() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepSeconds(2);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("nha@gmail.com");
        sleepSeconds(2);

        //tại đng thời điểm này/step này thì confirm email textbox đang visible/displayed
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reconfirmEmailTextBox));

        // kiểm tra 1 element đang hiển thị
        Assert.assertTrue(driver.findElement(reconfirmEmailTextBox).isDisplayed());

    }

    @Test
    public void TC_02_Invisible_In_DOM() {
        // Điều kiện 2 - Element không xuất hện trên UI và vẫn có trong cây HTML
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepSeconds(2);
        //tại đng thời điểm này/step này thì confirm email textbox đang visible/displayed
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reconfirmEmailTextBox));
        // kiểm tra 1 element k hiển thị
        Assert.assertFalse(driver.findElement(reconfirmEmailTextBox).isDisplayed());

    }
    @Test
    public void TC_03_Invisible_NOT_In_DOM() {
        // Điều kiện 3 - Element không xuất hện trên UI và k có trong cây HTML
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepSeconds(2);
        //tại đng thời điểm này/step này thì confirm email textbox đang visible/displayed
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reconfirmEmailTextBox));
        // kiểm tra 1 element k hiển thị
        Assert.assertFalse(driver.findElement(reconfirmEmailTextBox).isDisplayed());

    }


    @Test
    public void TC_03_Form() {
        Assert.assertTrue(driver.findElement(By.xpath("//form[@data-testid='royal_login_form']")).isDisplayed());
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
