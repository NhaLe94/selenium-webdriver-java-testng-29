package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_10_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;
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
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Test
    public void TC_01() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemInDropdown("span#speed-button","ul#speed-menu div","Slow");
        sleepSeconds(3);

        selectItemInDropdown("span#files-button","ul#files-menu div","ui.jQuery.js");
        sleepSeconds(3);

        selectItemInDropdown("span#number-button","ul#number-menu div","10");
        sleepSeconds(3);

        selectItemInDropdown("span#salutation-button","ul#salutation-menu div","Mr.");
        sleepSeconds(3);
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
    public void selectItemInDropdown(String parentCss, String childItemCss, String iTemTextExpected){
        driver.findElement(By.cssSelector(parentCss)).click();
        sleepSeconds(3);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));
        List<WebElement> allItem = driver.findElements(By.cssSelector(childItemCss));
        for (WebElement item: allItem){
            String textItem = item.getText();
          //  System.out.println("Text item = " + textItem);
            if (textItem.equals(iTemTextExpected)){
                item.click();
                break;
            }
        }
    }
}
