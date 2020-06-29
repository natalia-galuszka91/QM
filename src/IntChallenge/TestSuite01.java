package IntChallenge;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class TestSuite01 extends TestSetup{

    private String HompePageUrl = "https://qualityminds.de/";
    private String EmailAddress = "hello@qualityminds.de";
    private String flyerLinkExpected = "https://qualityminds.de/app/uploads/2018/11/Find-The-Mobile-Bug-Session.pdf";
    By submenu = By.cssSelector("ul[id='top-menu'] > li > ul");
    By pageTabMobile = By.id("team-tab-three-title-desktop");
    By flyerDownloadButton = By.xpath("//a[contains(@download, 'FLYER FIND THE BUG SESSION')]");
    By currentTab = By.xpath("//li[contains(@class, 'current-menu-item')]/a");

    @Test
    public void testCase_01() throws InterruptedException {
        driver.get(HompePageUrl);
        HompePage hp = new HompePage(driver);
        hp.acceptCookies();
        hp.selectTopMenuItem(6);
        String PageFromTopMenu = driver.getCurrentUrl();
        System.out.println(PageFromTopMenu);
        assertTrue("Email address not found", driver.findElement(By.linkText(EmailAddress)).isDisplayed());
        driver.navigate().back();
        hp.selectFooterMenuItem(3);
        Thread.sleep(1000);
        String PageFromFooter= driver.getCurrentUrl();
        System.out.println(PageFromFooter);
        assertEquals("Page displayed from top menu link != page from footer link", PageFromTopMenu, PageFromFooter);
    }

    @Test
    public void testCase_02() throws InterruptedException {
        driver.get(HompePageUrl);
        HompePage hp = new HompePage(driver);
        hp.acceptCookies();
        hp.hoverOnTopMenuItem(2);
        assertTrue("Submenu is not displayed", driver.findElement(submenu).isDisplayed());
        hp.selectSubmenuItem(2, 1, 3);
        assertTrue("Menu item is not highlighted", driver.findElement(currentTab).isEnabled());
        WAMTestingPage wam = new WAMTestingPage(driver);
        driver.findElement(pageTabMobile).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(flyerDownloadButton));
        String flyerLinkActual = driver.findElement(flyerDownloadButton).getAttribute("href");
        System.out.println(flyerLinkActual);
        assertEquals("Download link incorrect", flyerLinkExpected, flyerLinkActual);
        driver.findElement(flyerDownloadButton).click();
        Thread.sleep(1000);
        assertTrue("File not downloaded", wam.isFileDownloaded("FLYER FIND THE BUG SESSION.pdf"));
    }

    @Test
    public void testCase_03() throws InterruptedException {
        driver.get(HompePageUrl);
        HompePage hp = new HompePage(driver);
        hp.acceptCookies();
        hp.selectTopMenuItem(4);
        CareerPage cp = new CareerPage(driver);
        cp.clickOnApply();
        ApplicationPage ap = new ApplicationPage(driver);
        Thread.sleep(5000);
        ap.submitWithEmptyFields();
        Thread.sleep(500);
        assertTrue("3 error messages not displayed", ap.fnameFieldHasError() && ap.lnameFieldHasError() && ap.emailFieldHasError());
        ap.submitFnameLname();
        Thread.sleep(500);
        assertTrue("Error message not displayed", ap.emailFieldHasError());
        ap.sumbitIncorrectEmail();
        Thread.sleep(500);
        assertTrue("Error message not displayed", ap.emailFieldIncorrect());
        ap.checkDataConsent();

    }
}
