package pages;

import org.openqa.selenium.WebDriver;

public class PythonSpeciPage extends BasePage<PythonSpeciPage> {
    public static final String pathName = "lessons/python-specialization/";

    public PythonSpeciPage(WebDriver driver) {
        super(driver, pathName);
    }
}
