package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import support.Search;
import support.ISearchData;
import java.util.List;

public class MenuBar extends BaseComponent<MenuBar> {

  @FindBy(xpath = "//div[@class = 'header2-menu header2-menu_main']//div[@class = 'header2-menu__item-wrapper']")
    List<WebElement> headerMenuItems;

  String subMenuItem = "./following-sibling::div//a[contains(@class, 'header2-menu__dropdown-link')]";

  public MenuBar(WebDriver driver) {
    super(driver);
  }

  public WebElement chooseHeaderMenuItem(String menuItemName) {
    ISearchData<WebElement> search = new Search<>(headerMenuItems, item -> item.getText().equals(menuItemName));
    WebElement headerMenuItem = search.searchFirstElement();
    action.moveToElement(headerMenuItem).build().perform();
    return headerMenuItem;
  }

  public WebElement chooseHeaderSubMenuItem(String menuItemName, String submenuItemName) {
    WebElement headerMenuItem = chooseHeaderMenuItem(menuItemName);
    List<WebElement> headerSubMenuItems = headerMenuItem.findElements(By.xpath(subMenuItem));
    ISearchData<WebElement> search = new Search<>(headerSubMenuItems, item -> item.getAttribute("title").equals(submenuItemName));
    WebElement headerSubMenuItem = search.searchFirstElement();;
    action.moveToElement(headerSubMenuItem).click().build().perform();
    return headerSubMenuItem;
  }
}