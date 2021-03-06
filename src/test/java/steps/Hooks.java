package steps;
import org.openqa.selenium.WebDriver;
import webdriverfactory.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

  public static WebDriver driver;
  public WebDriverFactory webDriverFactory;

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
