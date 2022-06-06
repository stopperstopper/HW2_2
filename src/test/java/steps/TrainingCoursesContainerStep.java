package steps;

import components.TrainingCoursesContainer;
import data.TrainingCoursesContainerData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import java.util.Collections;


public class TrainingCoursesContainerStep {
    public WebDriver driver;
    public TrainingCoursesContainerData trainingCoursesContainerData;
    public TrainingCoursesContainer trainingCoursesContainer;

    public TrainingCoursesContainerStep() {
        driver = Hooks.driver;
    }

    @When("Ищем курс с минимальной ценой")
    public void weSearchCourseByMinimumPrice() {
        trainingCoursesContainer = new TrainingCoursesContainer(driver);
        trainingCoursesContainerData = trainingCoursesContainer.searchCourseWithMinPrice();
    }

    @Then("Курс с минимальной ценой найден")
    public void coursesAreFoundByMinimumPrice() {
        trainingCoursesContainer.printCourseInfo(Collections.singletonList(trainingCoursesContainerData));
    }

    @When("Ищем курс с максимальной ценой")
    public void weSearchCourseByMaxPrice() {
        trainingCoursesContainer = new TrainingCoursesContainer(driver);
        trainingCoursesContainerData = trainingCoursesContainer.searchCourseWithMaxPrice();
    }

    @Then("Курс с максимальной ценой найден")
    public void coursesAreFoundByMaximumPrice() {
        trainingCoursesContainer.printCourseInfo(Collections.singletonList(trainingCoursesContainerData));
    }


}
