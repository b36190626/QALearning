import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class Task2Part1Case2Test {
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.json";
    BrowserCapabilities browserCap;

    @BeforeClass
    public void preCondition() throws IOException, ParseException {

        // Reading host URL from the config file using SettingsReader class
        SettingsReader settingsReader = new SettingsReader();
        settingsReader.readFile(CONFIG_FILE_PATH);
        browserCap = BrowserFactory.getBrowser(DriverType.FIREFOX);
        Browser.goToUrl(settingsReader.getValue("hostURL"));

    }

    @Test
    public void userInyerface() {

        // Step 1: Navigate to main page by URL
        BaseElement homePage = new BaseElement(By.xpath("//div[contains(@class, 'start')]"), "Home Page");
        homePage.isElementPresent();

        // Verifying the expected result "Main Page is Open." for step 1
        if (homePage.isDisplayed()) {
            Assert.assertTrue(true);
            System.out.println("Main Page is open");
        } else {
            System.out.println("Main page is not as expected");
        }

        // Step 2: Click HERE button
        BaseElement hereLink = new Button(By.xpath("//a[contains(@class, 'start__link')]"), "Here Link");
        hereLink.click();

        // Game Page
        BaseElement gameView = new BaseElement(By.xpath("//div[contains(@class, 'game')]"), "Game View");
        gameView.isElementPresent();

        // Verify
        if (gameView.isDisplayed()) {
            Assert.assertTrue(true);
            System.out.println("Game Page is open");
        } else {
            System.out.println("Game page is not as expected");
        }

        //Step 2 checking if the timer starts from zero or not
        BaseElement timerElement = new BaseElement(By.xpath("//div[contains(@class, 'timer')]"), "Timer");

        //Verify
        if (timerElement.isDisplayed() && timerElement.getElement().getText().equals("00:00:00")) {
            System.out.println("The timer starts from zero");
            Assert.assertTrue(true);
        } else {
            Assert.fail("The timer is not running.");
        }
    }
    @AfterClass
    public void postCondition(){
        Browser.driver.quit();
    }
}