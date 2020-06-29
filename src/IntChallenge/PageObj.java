package IntChallenge;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObj {

    protected WebDriver driver;

    public PageObj(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
