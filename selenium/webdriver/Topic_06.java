package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06 {

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
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Displayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //Assert.assertTrue(driver.findElement(By.cssSelector("input#email")).isDisplayed());
        if(driver.findElement(By.cssSelector("input#mail")).isDisplayed()){
            driver.findElement(By.cssSelector("input#mail")).sendKeys("Automation Testing");
            System.out.println("Email Textbox is displayed");
        }else {
            System.out.println("Email Textbox is not displayed");
        }

       // Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isDisplayed());

        if(driver.findElement(By.cssSelector("input#under_18")).isDisplayed()){
            driver.findElement(By.cssSelector("input#under_18")).click();
            System.out.println("Under 18 radio is displayed");
        }else {
            System.out.println("Under 18 radio is not displayed");
        }
       // Assert.assertTrue(driver.findElement(By.cssSelector("textarea#edu")).isDisplayed());

        if(driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()){
            driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation Testing");
            System.out.println("Education TextArea is displayed");
        }else {
            System.out.println("Education TextArea is not displayed");
        }
        //Assert.assertFalse(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed());
        if(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()){
            ((JavascriptExecutor)driver).executeScript("scroll(0,400)");
            System.out.println("Name User5 text is displayed");
        }else {
            System.out.println("Name User5 text is not displayed");
        }
    }

    @Test
    public void TC_02_Enable() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //Assert.assertTrue(driver.findElement(By.cssSelector("input#email")).isDisplayed());
        if(driver.findElement(By.cssSelector("input#mail")).isEnabled()){
            System.out.println("Email Textbox is enable");
        }else {
            System.out.println("Email Textbox is displayed");
        }

        if(driver.findElement(By.cssSelector("input#disable_password")).isEnabled()){
            System.out.println("Password Textbox is enable");
        }else {
            System.out.println("Password Textbox is displayed");
        }

        if(driver.findElement(By.cssSelector("input#check-disbaled")).isEnabled()){
            System.out.println("Checkbox is enable");
        }else {
            System.out.println("Checkbox is displayed");
        }

    }

    @Test
    public void TC_03_Selected() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.cssSelector("input#under_18")).click();

        driver.findElement(By.cssSelector("input#java")).click();
        Thread.sleep(1000);

        Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("input#java")).isSelected());

        driver.findElement(By.cssSelector("input#java")).click();
        Thread.sleep(1000);

        Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isSelected());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#java")).isSelected());


    }
    @Test
    public void TC_04_MailChip(){
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.cssSelector("input#email")).sendKeys("nhale@gmali.com");
        //case 1 number
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("12345");
        sleepSeconds(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());

        //case 2 lowercase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("auto");
        sleepSeconds(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());

        //case 3 uppercase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("AUTO");
        sleepSeconds(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());

        //case 4 special chars
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("@#$^&");
        sleepSeconds(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());

        //case 5 max lenght
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("12345678");
        sleepSeconds(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());

        //case 6 valid
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Auto123!@#");
        sleepSeconds(2);
        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());

        //case 7 empty
        driver.findElement(By.cssSelector("input#new_password")).clear();
        sleepSeconds(2);
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        sleepSeconds(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
    public void sleepSeconds(long timeInsecond) {
        try {
            Thread.sleep(timeInsecond *1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
