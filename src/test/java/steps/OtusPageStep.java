package steps;

import components.MenuBar;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.OtusPage;

public class OtusPageStep {
  public WebDriver driver;
  public OtusPage otusPage;
  public MenuBar menuBar;

  public OtusPageStep() {
    driver = Hooks.driver;
  }

  @Given("Главная страница {string} открыта")
  public void mainPageIsOpened(String pageTitle) {
    otusPage = new OtusPage(driver);
    otusPage.open();
  }

  @When("Открываем меню {string} и выбираем {string}")
  public void weOpenMenuAndChooseSubmenu(String menuItemName, String submenuItemName) {
    menuBar = otusPage.getHeaderMenu();
    menuBar.chooseHeaderSubMenuItem(menuItemName, submenuItemName);
  }
}
