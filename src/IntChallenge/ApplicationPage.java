package IntChallenge;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.AssertJUnit.assertTrue;

public class ApplicationPage extends PageObj{

    By applyButtonInput = By.cssSelector("input[type = 'submit']");
    By fnameDataField = By.xpath("//div[contains(@class, 'contact-form-first-name')]/div/input");
    By lnameDataField = By.xpath("//div[contains(@class, 'contact-form-last-name')]/div/input");
    By emailDataField = By.xpath("//input[@placeholder='Email*']");
    By fieldErrorMessage = By.xpath("//span[@class='parsley-required']");
    By emailErrorMessage = By.xpath("//span[@class='parsley-type']");
    By dataConsentCheckbox = By.xpath("//div[contains(@class, 'consent-field')]/div/label/input");

    public ApplicationPage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, 10);
    Actions action = new Actions(driver);

    public void submitWithEmptyFields() {

        wait.until(ExpectedConditions.elementToBeClickable(applyButtonInput));
        action.moveToElement(driver.findElement(applyButtonInput), 5, 5).click().perform();
    }

    public boolean fnameFieldHasError() {

        try {
            WebElement fnameErrorMssg = driver.findElement(By.xpath("//div[contains(@class, 'contact-form-first-name')]//span[@class='parsley-required']"));
            String errorMessage = fnameErrorMssg.getText();
            System.out.println("First name field has error message: " + errorMessage);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean lnameFieldHasError() {

        try {
            WebElement lnameErrorMssg = driver.findElement(By.xpath("//div[contains(@class, 'contact-form-last-name')]//span[@class='parsley-required']"));
            String errorMessage = lnameErrorMssg.getText();
            System.out.println("Last name field has error message: " + errorMessage);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean emailFieldHasError() {

        try {
            WebElement emailErrorMssg = driver.findElement(By.xpath("//input[@placeholder='Email*']/parent::div/parent::div//span[@class='parsley-required']"));
            String errorMessage = emailErrorMssg.getText();
            System.out.println("Email field has error message: " + errorMessage);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void submitFnameLname() {

        driver.findElement(fnameDataField).sendKeys("a");
        driver.findElement(lnameDataField).sendKeys("b");
        wait.until(ExpectedConditions.elementToBeClickable(applyButtonInput));
        action.moveToElement(driver.findElement(applyButtonInput), 5, 5).click().perform();
    }

    public void sumbitIncorrectEmail() {

        driver.findElement(emailDataField).sendKeys("a");
        wait.until(ExpectedConditions.elementToBeClickable(applyButtonInput));
        action.moveToElement(driver.findElement(applyButtonInput), 5, 5).click().perform();
    }

    public boolean emailFieldIncorrect() {
        try {
            WebElement emailIncorrectMssg = driver.findElement(By.xpath("//input[@placeholder='Email*']/parent::div/parent::div//span[@class='parsley-type']"));
            String errorMessage = emailIncorrectMssg.getText();
            System.out.println("Email field has error message: " + errorMessage);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void checkDataConsent() {
        wait.until(ExpectedConditions.elementToBeClickable(dataConsentCheckbox));
        driver.findElement(dataConsentCheckbox).click();
        assertTrue("Checkbox not checked", driver.findElement(dataConsentCheckbox).isEnabled());
    }

}
