package steps;

import components.CoursesContainerAll;
import data.CoursesContainerData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;



public class CoursesContainerStep {
    public WebDriver driver;
    public CoursesContainerAll coursesContainerAll;
    public List<CoursesContainerData> coursesContainerData;

    public CoursesContainerStep() {
        driver = Hooks.driver;
    }

    @When("Находим курс {string} и кликаем на него")
    public void weSearchCourseByNameAndClickOnIt(String courseName) {
        coursesContainerAll = new CoursesContainerAll(driver);
        coursesContainerAll.clickCourseWithName(courseName);
    }

    @When("^Ищем курсы после (.+)$")
    public void weSearchCourseByDate(String courseDate) {
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter
                        .ofPattern("dd MMMM yyyy")
                        .withLocale(new Locale("ru"));
        LocalDate date = LocalDate.parse(courseDate, dateTimeFormatter);
        coursesContainerAll = new CoursesContainerAll(driver);
        coursesContainerData = coursesContainerAll.searchCoursesByDate(date);
    }

    @Then("Курсы после указанной даты найдены")
    public void courseIsFoundAfterASpecifiedDate() {
        coursesContainerAll.printCourseInfo(coursesContainerData);
    }
}
