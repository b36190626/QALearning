import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;


import org.json.simple.parser.ParseException;

public class Task2Part2and3Test {
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.json";
    BrowserCapabilities browserCap;

    //According to lesson, implementing page structure in general. I believe I can fetch those from config.json also. But would it be a correct way?
    private final String homePageLoc = "//div[contains(@class, 'start')]";
    private final String hereLinkLoc = "//a[contains(@class, 'start__link')]";
    private final String gameViewLoc = "//div[contains(@class, 'game')]";
    private final String helpButtonLoc = "//a[contains(@class, 'help-form__help-button')]";
    private final String helpResponseLoc = "//div[contains(@class, 'help-form__response')]";
    private final String timerElementLoc = "//div[contains(@class, 'timer')]";
    private final String emailInputLoc = "//input[@placeholder='Your email']";
    private final String domainInputLoc = "//input[@placeholder='Domain']";
    private final String passwordInputLoc = "//input[@placeholder='Choose Password']";
    private final String termsCheckboxLoc = "//span[@class='checkbox__box']";
    private final String dropDownLoc = "//div[@class='dropdown__opener']";
    private final String dropDownListLoc = "dropdown__list-item";
    private final String nextButtonLoc = "//a[(@class= 'button--secondary')]";
    private final String secondCardLoc = "//div[contains(@class, 'avatar-and-interests-page')]";

    @BeforeMethod
    public void setUP() throws IOException, ParseException {

        // Reading host URL from the config file using SettingsReader class
        SettingsReader settingsReader = new SettingsReader();
        settingsReader.readFile(CONFIG_FILE_PATH);
        browserCap = BrowserFactory.getBrowser(DriverType.FIREFOX);
        Browser.goToUrl(settingsReader.getValue("hostURL"));

    }

    @Test(priority = 1)
    public void helpForm() {

        // Step 1: Navigate to main page by URL
        BaseElement homePage = new BaseElement(By.xpath(homePageLoc),"Home Page");
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
        BaseElement hereLink = new Button(By.xpath(hereLinkLoc), "Here Link");
        hereLink.click();

        //Game Page
        BaseElement gameView = new BaseElement(By.xpath(gameViewLoc), "Game View");
        gameView.isElementPresent();

        //Verify
        if (gameView.isDisplayed()) {
            Assert.assertTrue(true);
            System.out.println("Game Page is open");
        }
        else{
            System.out.println("Game page is not as expected");
        }

        // Step 3: Click Help button on the Help form
        // Prepared for the config.json, if we put these into the config.json. Name field and
        // locator field can be defined as settingsReader.getMap or we can just use getValue(key) method for locators.
        BaseElement helpButton = new Button(By.xpath(helpButtonLoc),"Help Button");
        helpButton.click();

        //Verify
        BaseElement helpResponse = new Button(By.xpath(helpResponseLoc),"Help Response");
        if (helpResponse.isDisplayed()) {
            Assert.assertTrue(true);
            System.out.println("Help Response is displayed");
        } else {
            System.out.println("Help response is not as expected");
        }
    }

    @Test(priority = 2)
    public void timer() {

        // Step 1: Navigate to main page by URL
        BaseElement homePage = new BaseElement(By.xpath(homePageLoc),"Home Page");
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
        BaseElement hereLink = new Button(By.xpath(hereLinkLoc), "Here Link");
        hereLink.click();

        //Game Page
        BaseElement gameView = new BaseElement(By.xpath(gameViewLoc), "Game View");
        gameView.isElementPresent();

        //Verify
        if (gameView.isDisplayed()) {
            Assert.assertTrue(true);
            System.out.println("Game Page is open");
        }
        else{
            System.out.println("Game page is not as expected");
        }

        //Checking if the timer starts from zero or not
        BaseElement timerElement = new BaseElement(By.xpath(timerElementLoc), "Timer");

        //Verify
        if (timerElement.isDisplayed() && timerElement.getElement().getText().equals("00:00:00")) {
            System.out.println("The timer starts from zero");
            Assert.assertTrue(true);
        } else {
            Assert.fail("The timer is not running.");
        }

    }
    @Test(priority = 3)
    public void validPw() {

        // Step 1: Navigate to main page by URL
        BaseElement homePage = new BaseElement(By.xpath(homePageLoc), "Home Page");
        homePage.isElementPresent();

        // Verifying the expected result "Main Page is Open." for step 1
        if (homePage.isDisplayed()) {
            Assert.assertTrue(true);
            System.out.println("Main Page is open");
        } else {
            System.out.println("Main page is not as expected");
        }

        // Step 2: Click HERE button
        BaseElement hereLink = new Button(By.xpath(hereLinkLoc), "Here Link");
        hereLink.click();

        //Game Page
        BaseElement gameView = new BaseElement(By.xpath(gameViewLoc), "Game View");
        gameView.isElementPresent();

        //Verify
        if (gameView.isDisplayed()) {
            Assert.assertTrue(true);
            System.out.println("Game Page is open");
        } else {
            System.out.println("Game page is not as expected");
        }
        //Step 3 -> Input random valid email, valid random pw, terms and conditions checkbox, button 2 next card.
        //(Expected Result: The second card is open)

        // find the email input field and enter a random valid email /
        BaseElement emailInput = new BaseElement(By.xpath(emailInputLoc), "e-mail"); // BY.XPATH CONTAINS ILE BAK! ID ILE BULAMIYOR!
        String email = BaseForm.generateRandomEmail();
        emailInput.sendKeys(email);

        // find the second part of email field, clear and put domain like -example-.com
        BaseElement domainInput = new BaseElement(By.xpath(domainInputLoc), "e-mail");
        String domain = BaseForm.putDomain();
        domainInput.sendKeys(domain);

        //find the pw input field and enter a valid random password
        BaseElement passwordInput = new BaseElement(By.xpath(passwordInputLoc),"password");
        String password = BaseForm.generateRandomPassword(email);
        passwordInput.sendKeys(password);

        //find dropdown menu end select postfix of domain. I.e com.
        BaseElement dropDown = new BaseElement(By.xpath(dropDownLoc), "ComboBox");
        dropDown.click();
        BaseElement dropDownList = new BaseElement(By.className(dropDownListLoc), "CB List");
        dropDownList.comboBoxPicker(".com");

        // find and click the terms and conditions checkbox
        //BaseElement termsCheckbox = new BaseElement(By.xpath(termsCheckboxLoc),"Terms&Conditions");
        CheckBox termsCheckbox = new CheckBox(By.xpath(termsCheckboxLoc),"Terms&Conditions");
        termsCheckbox.click();

        // find and click the button to navigate to the next card
        BaseElement nextButton = new BaseElement(By.xpath(nextButtonLoc), "NextCard Button");
        nextButton.click();

        //The Second Card Page
        BaseElement secondCard = new BaseElement(By.xpath(secondCardLoc), "The Second Card");
        secondCard.isElementPresent();

        //Verify the second card is open or not
        if (secondCard.isElementPresent()) {
            Assert.assertTrue(true);
            System.out.println("The second card is open");
        }
        else{
            System.out.println("The second card isn't open");
        }

    }
    @Test(priority = 4)
    public void inValidPw() {

        // Step 1: Navigate to main page by URL
        BaseElement homePage = new BaseElement(By.xpath(homePageLoc), "Home Page");
        homePage.isElementPresent();

        // Verifying the expected result "Main Page is Open." for step 1
        if (homePage.isDisplayed()) {
            Assert.assertTrue(true);
            System.out.println("Main Page is open");
        } else {
            System.out.println("Main page is not as expected");
        }

        // Step 2: Click HERE button
        BaseElement hereLink = new Button(By.xpath(hereLinkLoc), "Here Link");
        hereLink.click();

        //Game Page
        BaseElement gameView = new BaseElement(By.xpath(gameViewLoc), "Game View");
        gameView.isElementPresent();

        //Verify
        if (gameView.isDisplayed()) {
            Assert.assertTrue(true);
            System.out.println("Game Page is open");
        } else {
            System.out.println("Game page is not as expected");
        }
        //Step 3 -> Input random valid email, valid random pw, terms and conditions checkbox, button 2 next card.
        //(Expected Result: The second card is open)

        // find the email input field and enter a random valid email /
        BaseElement emailInput = new BaseElement(By.xpath(emailInputLoc), "e-mail"); // BY.XPATH CONTAINS ILE BAK! ID ILE BULAMIYOR!
        String email = BaseForm.generateRandomEmail();
        emailInput.sendKeys(email);

        // find the second part of email field, clear and put domain like -example-.com
        BaseElement domainInput = new BaseElement(By.xpath(domainInputLoc), "e-mail");
        String domain = BaseForm.putDomain();
        domainInput.sendKeys(domain);

        //find the pw input field and enter a valid random password
        BaseElement passwordInput = new BaseElement(By.xpath(passwordInputLoc),"password");
        passwordInput.sendKeys("invalidpassword");

        //find dropdown menu end select postfix of domain. I.e com.
        BaseElement dropDown = new BaseElement(By.xpath(dropDownLoc), "ComboBox");
        dropDown.click();
        BaseElement dropDownList = new BaseElement(By.className(dropDownListLoc), "CB List");
        dropDownList.comboBoxPicker(".com");

        // find and click the terms and conditions checkbox
        //BaseElement termsCheckbox = new BaseElement(By.xpath(termsCheckboxLoc),"Terms&Conditions");
        CheckBox termsCheckbox = new CheckBox(By.xpath(termsCheckboxLoc),"Terms&Conditions");
        termsCheckbox.click();

        // find and click the button to navigate to the next card
        BaseElement nextButton = new BaseElement(By.xpath(nextButtonLoc), "NextCard Button");
        nextButton.click();

        //The Second Card Page
        BaseElement secondCard = new BaseElement(By.xpath(secondCardLoc), "The Second Card");

        //Verify the second card is open or not
        if (!secondCard.isElementPresent()) {
            Assert.assertTrue(true);
            System.out.println("The second card isn't open");
        }
        else{
            System.out.println("The second card is open");
            Assert.fail("The second card is open");
        }

    }

    @AfterMethod
    public void tearDown(){
        Browser.driver.quit();
    }
}