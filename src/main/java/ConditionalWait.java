import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class ConditionalWait {

    private WebDriverWait webDriverWait;
    private int timeoutInSeconds;
    private int pollingIntervalInSeconds;

    public ConditionalWait(WebDriverWait webDriverWait, int timeoutInSeconds, int pollingIntervalInSeconds) {
        this.webDriverWait = webDriverWait;
        this.timeoutInSeconds = timeoutInSeconds;
        this.pollingIntervalInSeconds = pollingIntervalInSeconds;
        this.webDriverWait.withTimeout(Duration.ofSeconds(timeoutInSeconds));
        this.webDriverWait.pollingEvery(Duration.ofSeconds(pollingIntervalInSeconds));
    }

    public <T> T waitForDisplayed(BaseElement element) {
        return (T) webDriverWait.until(ExpectedConditions.visibilityOf(element.getElement()));
    }

    public <T> T waitForToBeClickable(BaseElement element) {
        return (T) webDriverWait.until(ExpectedConditions.elementToBeClickable(element.getElement()));
    }

    public <T> T waitForTrue(Function<WebDriverWait, T> condition) {
        return condition.apply(webDriverWait);
    }

    public <T> T waitFor(ExpectedCondition<T> condition) {
        return webDriverWait.until(condition);
    }
}
