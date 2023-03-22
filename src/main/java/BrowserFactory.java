import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory extends Browser{
    private BrowserCapabilities capabilities;
    private static final Browser browser = new Browser();

    public BrowserFactory(){
        super();
    }

//    public static WebDriver createDriver() {
//        // code to create a WebDriver instance using the driver options
//        Capabilities options = capabilities.getCaps();
//        driver = new ChromeDriver((ChromeDriverService) options);
//        // ??
//        return browser.getDriver();
//    }

    public static BrowserCapabilities getBrowser(DriverType type) {
        BrowserCapabilities capabilities;

        switch (type){
            case CHROME:
                capabilities = new ChromeCaps();
                Browser.driver = new ChromeDriver();
                browser.getDriver();
                break;
            case FIREFOX:
                capabilities = new FirefoxCaps();
                driver = new FirefoxDriver();
                browser.getDriver();
                break;
            default:
                capabilities = new FirefoxCaps();
                break;
        }
        return capabilities;
        //create a browser and decide the driver. if-else. if(driver == null{add.arguments + create driver}) else = return browser
        //IDriverOptions options = capabilities.getCaps();
        //Fetch Drivertype - config.json
        //return browser;
    }
    public Capabilities getCaps() {
        return capabilities.getCaps();

    }
}