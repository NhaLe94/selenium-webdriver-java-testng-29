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
    By errorFirstName = By.id("txtFirstname-error");
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
        driver.findElement(txtFirstName).sendKeys("Lucy Le");
    }
    public void enterInvaildEmail(String strInvalidEmail){
        driver.findElement(txtEmail).sendKeys("lucyle90@@gmail.com");
    }

    public void enterConfirmEmail(String strConfirmEmail){
        driver.findElement(txtCEmail).sendKeys("lucyle90@gmail.com");
    }
    public void enterPassword(String strPassword){
        driver.findElement(txtPassword).sendKeys("123456");
    }
    public void enterConfirmPassword(String strConfirmPassword){
        driver.findElement(txtCPassword).sendKeys("123456");
    }
    public void enterPhone(String strPhone){
        driver.findElement(txtPhone).sendKeys("0987654321");
    }
    public void clickSignUp(String strSignUp){
        driver.findElement(btnSignUp).click();
    }


}
