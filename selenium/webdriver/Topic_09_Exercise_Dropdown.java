package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_09_Exercise_Dropdown {

    WebDriver driver;
    String firstName = "Lucy", lastName = "Le", emailAddress = getEmailAddress(), password = "1234567890";
    String companyName = "Testing";
    String day = "20", month = "May", year = "1993";
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
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Register() {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);

       Select dayDropdown = new Select(driver.findElement(By.name("DateOfBirthDay")));
        dayDropdown.selectByVisibleText(day);
        // verfy dropdown này là single (k phải multiple)
        Assert.assertFalse(dayDropdown.isMultiple());
        //List<WebElement> dayOptions = day.getOptions();
        Assert.assertEquals(dayDropdown.getOptions().size(), 32);
        new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
        new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);


        driver.findElement(By.id("Email")).sendKeys(emailAddress);
        driver.findElement(By.id("Company")).sendKeys(companyName);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
        driver.findElement(By.cssSelector("button#register-button")).click();
        sleepSeconds(3);

    }

    @Test
    public void TC_02_Login() {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.cssSelector("a.ico-login")).click();
        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.login-button")).click();
        sleepSeconds(3);

        driver.findElement(By.cssSelector("a.ico-account")).click();
        sleepSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), lastName);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), emailAddress);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"), companyName);
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
    public String getEmailAddress(){
        Random rand = new Random();
        return "automation" + rand.nextInt(99999) + "@gmail.net";

    }
}
