import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxCaps extends BrowserCapabilities {
    private FirefoxOptions options;

    @Override
    public FirefoxOptions getCaps() {
        return options;
    }
}