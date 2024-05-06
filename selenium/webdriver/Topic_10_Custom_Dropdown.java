package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
            System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        }

        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
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

    @Test
    public void TC_02() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInDropdown("i.dropdown.icon","div.item>span.text","Christian");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");
        sleepSeconds(3);

        selectItemInDropdown("i.dropdown.icon","div.item>span.text","Jenny Hess");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
        sleepSeconds(3);

        selectItemInDropdown("i.dropdown.icon","div.item>span.text","Matt");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");
        sleepSeconds(3);

    }
    @Test
    public void TC_03() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInDropdown("li.dropdown-toggle","ul.dropdown-menu a","Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
        sleepSeconds(3);

        selectItemInDropdown("li.dropdown-toggle","ul.dropdown-menu a","First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
        sleepSeconds(3);

        selectItemInDropdown("li.dropdown-toggle","ul.dropdown-menu a","Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
        sleepSeconds(3);

    }

    @Test
    public void TC_04_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemInEditableDropdown("input.search","div.item span","Algeria");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Algeria");
        sleepSeconds(3);

        selectItemInEditableDropdown("input.search","div.item span","Australia");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Australia");
        sleepSeconds(3);

        selectItemInEditableDropdown("input.search","div.item span","Belgium");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belgium");
        sleepSeconds(3);

    }

    @Test
    public void TC_05_NopCommerce() {
        driver.get("https://demo.nopcommerce.com/register");
        selectItemInDropdown("select[name='DateOfBirthDay']","select[name='DateOfBirthDay']>option","18");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']>option[value='18']")).isSelected());
        sleepSeconds(3);

        selectItemInDropdown("select[name='DateOfBirthMonth']","select[name='DateOfBirthMonth']>option","September");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']>option[value='9']")).isSelected());
        sleepSeconds(3);

        selectItemInDropdown("select[name='DateOfBirthYear']","select[name='DateOfBirthYear']>option","1995");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']>option[value='1995']")).isSelected());
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

    public void selectItemInEditableDropdown(String parentCss, String childItemCss, String iTemTextExpected){
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
