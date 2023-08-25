package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {

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
        driver.get("https://demo.nopcommerce.com/register");
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_ID() {
        driver.findElement(By.id("FirstName")).sendKeys("Nha Le");

    }

    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("header-logo"));

    }

    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("DateOfBirthDay"));

    }
    @Test
    public void TC_04_TagName() {
        driver.findElements(By.tagName("input"));

    }

    @Test
    public void TC_05_LinkText() {
        // lấy hết toàn bộ linktext đó, độ chính xác cao
        driver.findElements(By.linkText("Shipping & returns"));


    }

    @Test
    public void TC_06_Partial_LinkText() {
        //chỉ cần lấy 1 đoạn(đầu-giữa-cuối), độ chinh xác k cao
        driver.findElements(By.partialLinkText("Apply for"));
        driver.findElements(By.partialLinkText("Apply for vendor"));
        driver.findElements(By.partialLinkText("vendor account"));

    }

    @Test
    public void TC_07_XPath() {
        driver.findElements(By.xpath("//*[@id=\"FirstName\"]"));

    }

    @Test
    public void TC_08_Css() {
        driver.findElements(By.cssSelector("input[id='FirstName']"));


    }

    @AfterClass
    public void afterClass() {

        //driver.quit();
    }
}
