package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_21_Upload_File {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    String giftImg = "gift.jpg";
    String snowImg = "snow.jpg";
    String xmasImg = "xmas.png";

    String giftFilePath = projectPath + "\\uploadFiles\\" + giftImg;
    String snowFilePath = projectPath + "\\uploadFiles\\" + snowImg;
    String xmasFilePath = projectPath + "\\uploadFiles\\" + xmasImg;


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
    public void TC_01_Single_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By upload = By.cssSelector("input[name='files[]']");
        driver.findElement(upload).sendKeys(giftFilePath);
        driver.findElement(upload).sendKeys(snowFilePath);
        driver.findElement(upload).sendKeys(xmasFilePath);
        // verify
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + giftImg + "']" )).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + snowImg + "']" )).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + xmasImg + "']" )).isDisplayed());
        List<WebElement> startButtons = driver.findElements(By.cssSelector("td>button.start"));
        for(WebElement button: startButtons){
            button.click();
        }
        // verify file upload success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + giftImg + "']" )).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + snowImg + "']" )).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + xmasImg + "']" )).isDisplayed());

    }

    @Test
    public void TC_02_Multiple_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By upload = By.cssSelector("input[name='files[]']");
        driver.findElement(upload).sendKeys(giftFilePath + "\n" + snowFilePath + "\n" + xmasFilePath );

        // verify
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + giftImg + "']" )).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + snowImg + "']" )).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + xmasImg + "']" )).isDisplayed());
        List<WebElement> startButtons = driver.findElements(By.cssSelector("td>button.start"));
        for(WebElement button: startButtons){
            button.click();
        }
        // verify file upload success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + giftImg + "']" )).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + snowImg + "']" )).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + xmasImg + "']" )).isDisplayed());

    }

    @AfterClass
    public void afterClass() {

        driver.quit();
    }


}
