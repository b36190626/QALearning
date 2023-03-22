import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeCaps extends BrowserCapabilities {

    private ChromeOptions options;

    //ChromeDriverService chromeService;
    //private SettingsReader settingsReader;

    @Override
    public ChromeOptions getCaps() {
        return options;
    }
}