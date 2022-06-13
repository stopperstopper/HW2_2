package components;

import data.CoursesContainerData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import support.Search;
import support.ISearchData;
import support.Printer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CoursesContainerAll extends BaseComponent<CoursesContainerAll> {

  private List<CoursesContainerData> coursesContainerData = new ArrayList<>();

  public CoursesContainerAll(WebDriver driver) {
    super(driver);
  }

  String  nameCourse = ".//div[contains(@class, 'lessons__new-item-title')]";

  String startDateCourse = ".//div[@class = 'lessons__new-item-start'] | .//div[@class = 'lessons__new-item-courses']//following-sibling::div[@class = 'lessons__new-item-time']";

  private String getCoursesName(WebElement courseContainer) {
    webElementShouldBeVisible(courseContainer);
    return courseContainer.findElement(By.xpath(nameCourse)).getText().trim();
  }

  private String getCoursesContainerStartDate(WebElement courseContainer) {
    webElementShouldBeVisible(courseContainer);
    return courseContainer.findElement(By.xpath(startDateCourse)).getText().trim();
  }

  private boolean isDateWithYear(String date) {
    Pattern pattern = Pattern.compile(".*?((\\d{4})(?:\\s*года)).*");
    Matcher matcher = pattern.matcher(date);
    return matcher.find();
  }

  private String parseYear(String date) {
    Pattern pattern = Pattern.compile("(\\d{4})");
    Matcher matcher = pattern.matcher(date);
    return matcher.find() ? matcher.group(1) : null;
  }

  private LocalDate parseStringToDate(String date) {
    String newDate = null;
    String year;
    Pattern pattern = Pattern.compile("(\\d{1,2}\\s*[а-я]+)");
    Matcher matcher = pattern.matcher(date);

    if (matcher.find()) {
      newDate = matcher.group(1);
    }

    year = isDateWithYear(date) ? parseYear(date) : Integer.toString(LocalDate.now().getYear());
    newDate += " " + year;
    DateTimeFormatter dateTimeFormatter =
        DateTimeFormatter
        .ofPattern("dd MMMM yyyy")
        .withLocale(new Locale("ru"));
    return LocalDate.parse(newDate, dateTimeFormatter);
  }

  private List<WebElement> searchCoursesWithDate() {
    List<WebElement> courseStartDates = driver.findElements((By.xpath(startDateCourse)));
    ISearchData<WebElement> finder = new Search<>(courseStartDates, (WebElement courseDate) -> courseDate.getText().matches("([^В].*?\\d{1,2}\\s*[а-я]+)"));
    return finder.searchAllElements().stream().map(course -> course.findElement(By.xpath("./../.."))).collect(Collectors.toList());
  }

  private void parsecoursesContainer(List<WebElement> coursesContainerWebElements) {
    coursesContainerData.clear();
    for (WebElement coursesContainerWebElement : coursesContainerWebElements) {
      String name = getCoursesName(coursesContainerWebElement);
      String startDateText = getCoursesContainerStartDate(coursesContainerWebElement);
      LocalDate startDate = parseStringToDate(startDateText);
      CoursesContainerData coursesContainerData = new CoursesContainerData(name, startDate);
      this.coursesContainerData.add(coursesContainerData);
    }
  }

  private WebElement searchCourseByName(String courseName) {
    List<WebElement> courseNames = driver.findElements((By.xpath("//div[@class = 'lessons']/a")));
    return courseNames.stream().filter(c -> c.getText().contains(courseName)).findFirst().get();
  }

  public void clickCourseWithName(String courseName) {
    action.moveToElement(searchCourseByName(courseName)).click().build().perform();
  }

  public List<CoursesContainerData> searchCoursesByDate(LocalDate courseDate) {
    List<WebElement> courses = searchCoursesWithDate();
    parsecoursesContainer(courses);
    ISearchData<CoursesContainerData> search = new Search<>(coursesContainerData, d -> d.getStartDate().isAfter(courseDate));
    return search.searchAllElements();
  }

  public void printCourseInfo(List<CoursesContainerData> coursesContainerData) {
    Printer<CoursesContainerData> printer = new Printer<>(coursesContainerData, f -> System.out.println("Название курса: " + f.getName() + ", дата старта " + f.getStartDate()));
    printer.print();
  }
}

