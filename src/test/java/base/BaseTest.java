package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected static WebDriver driver;

    protected void setUpDriver() {

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();

        // Disable Chrome password manager
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        // Disable Chrome password breach warning
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);

        // Prevent Chrome password-store UI
        options.addArguments("--password-store=basic");

        driver = WebDriverManager
                .chromedriver()
                .capabilities(options)
                .create();
    }

    protected void tearDownDriver() {

        if (driver != null) {
            driver.quit();
        }
    }
}