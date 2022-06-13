package pages;

import components.MenuBar;
import org.openqa.selenium.WebDriver;

public class OtusPage extends BasePage<OtusPage> {

  private MenuBar menuBar;
  public OtusPage(WebDriver driver) {
    super(driver, "");
    menuBar = new MenuBar(driver);
  }

  public MenuBar getHeaderMenu() {
    return menuBar;
  }
}
