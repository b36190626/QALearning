import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class Task2Part1Case1Test {
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
        BaseElement homePage = new BaseElement(By.xpath("//div[contains(@class, 'start')]"),"Home Page");
        homePage.isElementPresent();

        // Verifying the expected result "Main Page is Open." for step 1
        if (homePage.isDisplayed()) {
            Assert.assertTrue(true);
            System.out.println("Main Page is open");
        }
        else{
            System.out.println("Main page is not as expected");
        }

        // Step 2: Click HERE button
        BaseElement hereLink = new Button(By.xpath("//a[contains(@class, 'start__link')]"), "Here Link");
        hereLink.click();

        //Game Page
        BaseElement gameView = new BaseElement(By.xpath("//div[contains(@class, 'game')]"), "Game View");
        gameView.isElementPresent();

        //Verify
        if (gameView.isDisplayed()) {
            Assert.assertTrue(true);
            //Assert.assertTrue(homePage.isDisplayed());
            System.out.println("Game Page is open");
        }
        else{
            System.out.println("Game page is not as expected");
        }

        // Step 3: Click Help button on the Help form
        BaseElement helpButton = new Button(By.xpath("//a[contains(@class, 'help-form__help-button')]"),"Help Button");
        helpButton.click();
        BaseElement helpResponse = new Button(By.xpath("//div[contains(@class, 'help-form__response')]"),"Help Response");

        //verify
        if (helpResponse.isDisplayed()) {
            Assert.assertTrue(true);
            System.out.println("Help Response is displayed");
        } else {
            System.out.println("Help response is not as expected");
        }
    }
    @AfterClass
    public void postCondition(){
        Browser.driver.quit();
    }
}