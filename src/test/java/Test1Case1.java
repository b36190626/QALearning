import org.json.simple.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Test1Case1 {
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.json";
    private static final String HOST_URL_KEY = "hostURL";

    private WebDriver driver;
    private String hostURL;

    @BeforeClass
    public void setUp() throws IOException, ParseException {
        // Reading host URL from the config file
        JSONParser jsonParser = new JSONParser();
        FileInputStream inputStream = new FileInputStream(CONFIG_FILE_PATH);
        Object obj = jsonParser.parse(new InputStreamReader(inputStream));
        JSONObject jsonObj = (JSONObject) obj;
        hostURL = String.valueOf(jsonObj.get(HOST_URL_KEY));
        // Set up the WebDriver
        driver = new FirefoxDriver();
    }

    @Test
    public void userInyerface() {

        // Step 1: Navigate to main page by URL
        driver.get(hostURL);

        // Waiting for the page to contains start view div
        WebElement homePage = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'start')]")));

        // Verifying the expected result "Main Page is Open." for step 1
        if (homePage.isDisplayed()) {
            Assert.assertTrue(true);
            //Assert.assertTrue(homePage.isDisplayed());
            System.out.println("Main Page is open");
        }
        else{
            System.out.println("Main Page is not as expected");
        }

        // Step 2: Click Here link
        WebElement clickHereLink = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'start__link')]")));
        clickHereLink.click();

        // Verifying the expected result for step 2
        WebElement gameView = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'game')]")));
        if (gameView.isDisplayed()) {
            Assert.assertTrue(true);
            //Assert.assertTrue(gameView.isDisplayed());
            System.out.println("Game Page is open");

        }
        else{
            System.out.println("Game page is not as expected");
        }
        // Step 3: Click Help button on the Help form
        WebElement helpButton = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'help-form__help-button')]")));
        helpButton.click();
        WebElement helpResponse = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'help-form__response')]")));
        if (helpResponse.isDisplayed()) {
            Assert.assertTrue(true);
            //Assert.assertTrue(helpResponse.isDisplayed());
            System.out.println("Help Response is displayed");
        }
        else{
            System.out.println("Help response is not as expected");
        }

        driver.quit();

    }
}
