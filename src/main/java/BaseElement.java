import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseElement {
    protected By uniqueLocator;
    protected String elementName;
    WaitUtil waits = new WaitUtil();
    private static final Logger LOGGER = Logger.getLogger(BaseElement.class.getName());

    public BaseElement(By locator, String name) {
        this.uniqueLocator = locator;
        this.elementName = name;
        LOGGER.setLevel(Level.INFO);
    }

    protected WebElement getElement() {
        return Browser.driver.findElement(uniqueLocator);
        }


    public void click() {
        waits.waitForPresenceOfElement(uniqueLocator);
        waits.waitForElementToBeClickable(uniqueLocator);
        LOGGER.info(elementName + " is clicked");
        getElement().click();
    }

    public boolean isElementPresent() {
        LOGGER.info(elementName + " is found");
        return waits.waitForPresenceOfElement(uniqueLocator);
    }

    public void sendKeys(CharSequence... keysToSend) {
        BaseForm.clear(uniqueLocator);
        getElement().sendKeys(keysToSend);
    }
    public void comboBoxPicker(String postFix) {
        java.util.List<WebElement> options = Browser.driver.findElements(uniqueLocator);

        for (WebElement option : options) {
            if (option.getText().equals(postFix)) {
                option.click();
                break;
            }
        }
    }
    public boolean isDisplayed() {
        LOGGER.info(elementName + " is displayed");
        return getElement().isDisplayed();
    }

}