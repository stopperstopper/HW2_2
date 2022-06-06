package pages;

import components.MenuBar;
import org.openqa.selenium.WebDriver;

public class OtusPage extends BasePage<OtusPage> {
    private static final String PATH_NAME = "";
    private MenuBar menuBar;

    public OtusPage(WebDriver driver) {
        super(driver, PATH_NAME);
        menuBar = new MenuBar(driver);
    }

    public MenuBar getHeaderMenu() {
        return menuBar;
    }
}
