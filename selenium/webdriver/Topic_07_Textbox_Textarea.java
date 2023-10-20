package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_07_Textbox_Textarea {

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
//        driver.manage().window().maximize();
//        driver.get("https://www.facebook.com/");
//        driver.manage().window().maximize();
    }

    @Test
    public void Login_01_Empty_Email_And_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepSeconds(3);
        driver.findElement(By.cssSelector("button#send2")).click();
        //driver.findElement(By.xpath("//button[@class='button']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(), "This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(), "This is a required field.");


    }

    @Test
    public void Login_02_Invalid_Email() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepSeconds(3);

        driver.findElement(By.cssSelector("input#email")).sendKeys("lelucy@1234");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("1234567890");
        sleepSeconds(3);
        driver.findElement(By.cssSelector("button#send2")).click();
        sleepSeconds(3);
        //driver.findElement(By.xpath("//button[@class='button']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");



    }

    @Test
    public void Login_03_Invalid_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepSeconds(3);

        driver.findElement(By.cssSelector("input#email")).sendKeys("lelucy90@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("12345");
        sleepSeconds(3);
        driver.findElement(By.cssSelector("button#send2")).click();
        sleepSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");

    }

    @Test
    public void Login_04_Incorrect_Email_OR_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepSeconds(3);

        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");
        sleepSeconds(3);
        driver.findElement(By.cssSelector("button#send2")).click();
        sleepSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");

        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.net");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("12345645756745");
        sleepSeconds(3);
        driver.findElement(By.cssSelector("button#send2")).click();
        sleepSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");

    }
    @Test
    public void Login_05_Success() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepSeconds(3);
        // Register an account
        driver.findElement(By.xpath("//a[@class='button']")).click();
        driver.findElement(By.cssSelector("input#firstname")).sendKeys("Nha");
        driver.findElement(By.cssSelector("input#lastname")).sendKeys("Le");
        driver.findElement(By.cssSelector("input#email_address")).sendKeys("lelucy90@gmail.com");
        driver.findElement(By.cssSelector("input#password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@title='Register']")).click();


//        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.com");
//        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");
//        sleepSeconds(3);
//        driver.findElement(By.cssSelector("button#send2")).click();


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
