package IntChallenge;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CareerPage extends PageObj{

    public CareerPage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, 10);
    Actions action = new Actions(driver);

    public void clickOnApply() {

        WebElement applyButton = driver.findElement(By.xpath("//div[contains(@class,'career-intro-row')]//a[contains(@href, 'kontaktformular')]"));
        wait.until(ExpectedConditions.elementToBeClickable(applyButton));
        action.moveToElement(applyButton, 5, 5).click().perform();
    }
}
