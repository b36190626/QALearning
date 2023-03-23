import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.Set;

public class Browser {
    static WebDriver driver;

    public static void goToUrl(String url) {
        driver.get(url);
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public JavascriptExecutor getJsExecutor() {
        return (JavascriptExecutor) driver;
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public WebDriver getDriver() { //can also get it from config.json
        if (driver == null) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            driver = new FirefoxDriver(options);
        }
        return driver;
    }
}
