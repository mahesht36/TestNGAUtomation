import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.reporters.JUnitXMLReporter;
import org.uncommons.reportng.HTMLReporter;

@Listeners({HTMLReporter.class, JUnitXMLReporter.class})
public class Browseropening {

    ChromeDriver driver;
    public String baseUrl = "http://demo.guru99.com/test/newtours/";
    public String expected = null;
    public String actual = null;
    WebElelemtHelper webElelemtHelper = new WebElelemtHelper();

    @BeforeTest
    public void launchBrowser() {
        System.out.println("launching firefox browser");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(baseUrl);
    }

    @BeforeMethod
    public void verifyHomepageTitle() {
        String expectedTitle = "Welcome: Mercury Tours";
        webElelemtHelper.waitForElement(By.xpath("//div[@class='logo']"), driver);
//        webElelemtHelper.waitForElement(By.xpath("//div[contains(@class,'top-bottom right-column')]//span[contains(text(),'Accept All')]/ancestor::button//span[@class='mat-ripple mat-button-ripple']"), driver);
//        driver.findElement(By.xpath("//div[contains(@class,'top-bottom right-column')]//span[contains(text(),'Accept All')]/ancestor::button//span[@class='mat-ripple mat-button-ripple']")).submit();
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        System.out.println("Validation done");
    }

    //    @Test (groups = { "bonding", "strong_ties" })
    @Test(priority = 0)
    public void register() {
        webElelemtHelper.waitForElement(By.linkText("REGISTER"), driver);
        driver.findElement(By.linkText("REGISTER")).click();
        expected = "Register: Mercury Tours";
        actual = driver.getTitle();
        Assert.assertEquals(actual, expected);
    }

    @Test(priority = 1)
    public void support() {
        driver.findElement(By.linkText("SUPPORT")).click();
        expected = "Under Construction: Mercury Tours";
        actual = driver.getTitle();
        Assert.assertEquals(actual, expected);
    }

    @AfterMethod
    public void goBackToHomepage() {
        driver.findElement(By.linkText("Home")).click();
    }

    @AfterTest
    public void terminateBrowser() {
        driver.close();
    }
}

