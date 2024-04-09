package commons;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;


public class BasePage {
    public void openPageUrl(WebDriver driver, String url){
        driver.get(url);
    }
    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }
    public String getPageUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }
    public String getPageSourceCode(WebDriver driver){
        return driver.getPageSource();
    }
    public void backToPage(WebDriver driver){
        driver.navigate().back();
    }
    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }
    public void refreshCurrentPage(WebDriver driver){
        driver.navigate().refresh();
    }
    public Alert waitAlertPresence(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());
    }
    public void acceptToAlert(WebDriver driver){
        waitAlertPresence(driver).accept();
    }
    public void cancelToAlert(WebDriver driver){
        waitAlertPresence(driver).dismiss();
    }
    public String getAlertText(WebDriver driver){
        return waitAlertPresence(driver).getText();
    }
    public void senkeyToAlert(WebDriver driver, String keysToSend){
        waitAlertPresence(driver).sendKeys(keysToSend);
    }
    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }
    public WebElement getElement(WebDriver driver, String locator){
        return driver.findElement(By.xpath(locator));
    }
    public List<WebElement> getListElement(WebDriver driver, String locator){
        return driver.findElements(By.xpath(locator));
    }
    public By getByXpath(String locator){
        return By.xpath(locator);
    }
    public void clickToElement(WebDriver driver, String locator){
        getElement(driver, locator).click();
    }
    public void sendkeyToElement(WebDriver driver, String locator, String keysToSend){
        getElement(driver, locator).sendKeys(keysToSend);
    }
    public void selectItemDropdown(WebDriver driver, String locator, String textItem){
        new Select( getElement(driver, locator)).selectByVisibleText(textItem);
    }
    public String getSelectedItemDropdown(WebDriver driver, String locator){
        return new Select( getElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator){
        return new Select( getElement(driver, locator)).isMultiple();
    }
    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getElement(driver, parentLocator).click();
        sleepInSeconds(2);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemLocator)));
        sleepInSeconds(2);

        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                item.click();
                break;
            }
        }
    }
    public void sleepInSeconds(long timeInSeconds){
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public String getElementAttribute(WebDriver driver, String locator, String attributeName){
        return  getElement(driver, locator).getAttribute(attributeName);
    }
    public String getElementText(WebDriver driver, String locator) {
        return getElement(driver, locator).getText();
    }
    public String getElementCssValue(WebDriver driver, String locator, String propertyName){
        return getElement(driver, locator).getCssValue(propertyName);
    }
    public String getHexaColorFromRGBA(String rgbaValue){
        return Color.fromString(rgbaValue).asHex().toUpperCase();

    }
    public int getListElementNumber(WebDriver driver, String locator){
        return getListElement(driver, locator).size();
    }
    public void checkToCheckboxRadio(WebDriver driver, String locator){
        if(!getElement(driver, locator).isSelected()){
            getElement(driver, locator).click();
        }
    }
    public void uncheckToCheckbox(WebDriver driver, String locator){
        if(getElement(driver, locator).isSelected()){
            getElement(driver, locator).click();
        }
    }
    public boolean isElementDisplayed(WebDriver driver, String locator){
        return getElement(driver, locator).isDisplayed();
    }
    public boolean isElementEnabled(WebDriver driver, String locator){
        return getElement(driver, locator).isEnabled();
    }
    public boolean isElementSelected(WebDriver driver, String locator){
        return getElement(driver, locator).isSelected();
    }
    public void switchToIframe(WebDriver driver, String locator){
        driver.switchTo().frame(getElement(driver, locator));
    }
    public void switchToDefaultPage(WebDriver driver){
        driver.switchTo().defaultContent();
    }
    public void hoverToElement(WebDriver driver, String locator){
        new Actions(driver).moveToElement(getElement(driver, locator)).perform();
    }
    public void clickAndHoldToElement(WebDriver driver, String locator){
        new Actions(driver).clickAndHold(getElement(driver, locator)).perform();
    }
    public void doubleClickToElement(WebDriver driver, String locator){
        new Actions(driver).doubleClick(getElement(driver, locator)).perform();
    }
    public void rightClickToElement(WebDriver driver, String locator){
        new Actions(driver).contextClick(getElement(driver, locator)).perform();
    }
    public void dragAnddrDropElement(WebDriver driver, String sourcelocator, String targetLocator){
        new Actions(driver).dragAndDrop(getElement(driver, sourcelocator), getElement(driver, targetLocator)).perform();
    }
    public void pressKeyToElement(WebDriver driver, String locator, Keys keys){
        new Actions(driver).sendKeys(getElement(driver, locator), keys).perform();
    }
    public void waitForElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }
    public void waitForElementPresence(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(locator)));
    }
    public void waitForElementInvisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }
    public void waitForElementClickable(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }





}
