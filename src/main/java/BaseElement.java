import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BaseElement {
    protected By uniqueLocator;
    protected String elementName;
    WaitUtil waits = new WaitUtil();

    public BaseElement(By locator, String name) {
        this.uniqueLocator = locator;
        this.elementName = name;
    }

    protected WebElement getElement() {
        return Browser.driver.findElement(uniqueLocator);
        }


    public void click() {
        waits.waitForPresenceOfElement(uniqueLocator);
        waits.waitForElementToBeClickable(uniqueLocator);
        getElement().click();
    }

    public boolean isElementPresent() {
        return waits.waitForPresenceOfElement(uniqueLocator);
    }

    public void sendKeys(CharSequence... keysToSend) {
        getElement().sendKeys(keysToSend);
    }

    public boolean isDisplayed() {
        return getElement().isDisplayed();
    }
}