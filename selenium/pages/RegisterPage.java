package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testcase.Register;

public class RegisterPage {
    WebDriver driver;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    By txtFirstName  = By.id("txtFirstname");
    By errorEmptyFirstName = By.id("txtFirstname-error");
    By txtEmail = By.id("txtEmail");

    By errorEmail = By.id("txtEmail-error");

    By txtCEmail = By.id("txtCEmail");
    By errorCEmail = By.id("txtCEmail-error");
    By txtPassword = By.id("txtPassword");
    By errorPassword = By.id("txtPassword-error");
    By txtCPassword =By.id("txtCPassword");
    By errorCPassword = By.id("txtCPassword-error");

    By txtPhone = By.id("txtPhone");
    By errorPhone = By.id("txtPhone-error");
    By btnSignUp = By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']");

    public void enterFirstName(String strFirstName){
        driver.findElement(txtFirstName).sendKeys(strFirstName);
    }
    public void enterEmail(String strEmail){
        driver.findElement(txtEmail).sendKeys(strEmail);
    }

    public void enterConfirmEmail(String strConfirmEmail){
        driver.findElement(txtCEmail).sendKeys(strConfirmEmail);
    }
    public void enterPassword(String strPassword){
        driver.findElement(txtPassword).sendKeys(strPassword);
    }
    public void enterConfirmPassword(String strConfirmPassword){
        driver.findElement(txtCPassword).sendKeys(strConfirmPassword);
    }
    public void enterPhone(String strPhone){
        driver.findElement(txtPhone).sendKeys(strPhone);
    }
    public void clickSignUp(){
        driver.findElement(btnSignUp).click();
    }

    public String verifyErrorEmail()
    {
        return driver.findElement(errorEmail).getText();
    }

    public String verifyErrorCEmail()
    {
        return driver.findElement(errorCEmail).getText();
    }
    public String verifyErrorPassword()
    {
        return driver.findElement(errorPassword).getText();
    }
    public String verifyErrorCPassword()
    {
        return driver.findElement(errorCPassword).getText();
    }


    public String verifyErrorPhone()
    {
        return driver.findElement(errorPhone).getText();
    }





}
