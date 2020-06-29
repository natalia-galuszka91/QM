package IntChallenge;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class TestSetup {

    protected static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        //System.setProperty("webdriver.gecko.driver", "src/Drivers/geckodriver.exe");
        //driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "src/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void cleanUp(){

        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public static void tearDown(){

        driver.close();
    }
}
