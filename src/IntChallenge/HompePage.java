package IntChallenge;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HompePage extends PageObj{

    By cookieAcceptButton = By.xpath("//a[contains(@class, 'cc-allow')]");

    @FindBy(id="top-menu-nav")
    private WebElement topMenu;

    @FindBy(id="et-footer-nav")
    private WebElement footerMenu;


    public HompePage(WebDriver driver) {

        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, 10);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    Actions action = new Actions(driver);

    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
        driver.findElement(cookieAcceptButton).click();
    }

    public void selectTopMenuItem(Integer option) {
        WebElement topMenuItem = driver.findElement(By.xpath("//nav[@id='top-menu-nav']/ul/li[" + option + "]/a"));
        wait.until(ExpectedConditions.elementToBeClickable(topMenuItem));
        action.moveToElement(topMenuItem, 5, 5).click().perform();
    }

    public void hoverOnTopMenuItem(Integer option) {
        WebElement topMenuItem = driver.findElement(By.xpath("//nav[@id='top-menu-nav']/ul/li[" + option + "]/a"));
        wait.until(ExpectedConditions.elementToBeClickable(topMenuItem));
        action.moveToElement(topMenuItem).perform();
    }

    public void selectSubmenuItem(Integer tab, Integer column, Integer item) {
        hoverOnTopMenuItem(tab);
        WebElement submenuItem = driver.findElement(By.xpath("//ul[@id='top-menu']/li[" + tab + "]/ul/li[" + column + "]/ul/li[" + item + "]/a"));
        wait.until(ExpectedConditions.elementToBeClickable(submenuItem));
        action.moveToElement(submenuItem).click().perform();
    }

    public void selectFooterMenuItem(Integer option) {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        WebElement footerMenuItem = footerMenu.findElement(By.xpath("..//div/ul/li[" + option + "]/a"));
        wait.until(ExpectedConditions.elementToBeClickable(footerMenuItem));
        action.moveToElement(footerMenuItem, 5, 5).click().perform();
    }
}
