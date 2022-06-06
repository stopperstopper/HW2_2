package components;

import data.TrainingCoursesContainerData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import support.Search;
import support.ISearchData;
import support.Printer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TrainingCoursesContainer extends BaseComponent<CoursesContainerAll>{
    private List<TrainingCoursesContainerData> trainingCoursesContainerData = new ArrayList<>();
    String coursesList = ".lessons";
    String coursesName = ".lessons__new-item-title";
    String coursesPrice = ".lessons__new-item-price";

    public TrainingCoursesContainer(WebDriver driver) {
        super(driver);
    }

    private Integer parsePrice(String priceText) {
        String price = priceText.substring(0, priceText.indexOf("₽")).replace(" ", "");
        return Integer.parseInt(price);
    }

    private String getCoursesName(WebElement coursesContainer) {
        webElementShouldBeVisible(coursesContainer);
        return coursesContainer.findElement(By.cssSelector(coursesName)).getText().trim();
    }

    private String getCoursesContainerStartDate(WebElement coursesContainer) {
        webElementShouldBeVisible(coursesContainer);
        return coursesContainer.findElement(By.cssSelector(coursesPrice)).getText().trim();
    }
    private void parseTrainingCourseBlocks(List<WebElement> courseTrainingBlocksWebElems) {
        trainingCoursesContainerData.clear();

        for (WebElement courseTrainingBlocksWebElem : courseTrainingBlocksWebElems) {
            String name = getCoursesName(courseTrainingBlocksWebElem);
            String priceText = getCoursesContainerStartDate(courseTrainingBlocksWebElem);
            Integer price = parsePrice(priceText);
            TrainingCoursesContainerData trainingCoursesContainerData = new TrainingCoursesContainerData(name, price);
            this.trainingCoursesContainerData.add(trainingCoursesContainerData);
        }
    }

    public TrainingCoursesContainerData searchCourseWithMaxPrice() {
        List<WebElement> courses = driver.findElements(By.cssSelector(coursesList));
        parseTrainingCourseBlocks(courses);
        ISearchData<TrainingCoursesContainerData> search = new Search<>(trainingCoursesContainerData, Comparator.comparing(TrainingCoursesContainerData::getPrice));
        return search.searchMaxElement();
    }

    public TrainingCoursesContainerData searchCourseWithMinPrice() {
        List<WebElement> courses = driver.findElements(By.cssSelector(coursesList));
        parseTrainingCourseBlocks(courses);
        ISearchData<TrainingCoursesContainerData> search = new Search<>(trainingCoursesContainerData, Comparator.comparing(TrainingCoursesContainerData::getPrice));
        return search.searchMinElement();
    }

    public void printCourseInfo(List<TrainingCoursesContainerData> trainingCourseBlockEntities) {
        Printer<TrainingCoursesContainerData> printer = new Printer<>(trainingCourseBlockEntities, f -> System.out.println("Курс: " + f.getName() + ", цена " + f.getPrice()));
        printer.print();
    }
}
