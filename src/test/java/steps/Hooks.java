package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import webDriveFactory.WebDriverFactory;


public class Hooks {

    public static WebDriver driver;
    public static WebDriverFactory webDriverFactory;


    @Before
    public void weChooseChromeBrowser() {
        webDriverFactory = new WebDriverFactory();
        driver = webDriverFactory.getWebDriver("Chrome");
        webDriverMax();
    }

    @After
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void webDriverMax() {
        driver.manage().window().maximize();

    }


}
