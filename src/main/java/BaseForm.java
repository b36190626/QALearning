import org.openqa.selenium.By;

import java.awt.*;

public abstract class BaseForm {
    WaitUtil waits;
    BaseElement baseElement;
    private By uniqueFormLocator;
    public String name;

    public BaseForm(By uniqueFormLocator, String name) {
        this.uniqueFormLocator = uniqueFormLocator;
        this.name = name;
    }

    public boolean isPageOpened() {
        return baseElement.getElement().isDisplayed();
    }
    public Label getFormLabel(By uniqueFormLocator) {
        String custLabelAttribute = Browser.driver.findElement(By.xpath("//input[@id='customer_firstname']/preceding-sibling::label")).getAttribute("for");
        return null;
    }
}
