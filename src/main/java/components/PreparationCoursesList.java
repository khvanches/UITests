package components;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CourseDetailsPage;
import pages.PreparationCoursesPage;
import utils.GuiceScoped;

import java.util.Comparator;
import java.util.List;

public class PreparationCoursesList extends BasicComponentAbs<PreparationCoursesList> {

  @Inject
  public PreparationCoursesList(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  @FindBy(css = ".lessons__new-item")
  private List<WebElement> coursesList;

  public PreparationCoursesPage findCheapestCourse() {
    WebElement element = coursesList.stream()
        .reduce((x1, x2) -> getCourseCost(x1) < getCourseCost(x2) ? x1 : x2)
        .orElse(null);
    LOGGER.info(String.format("We define the cheapest course\n"
        + "course name is: %s\n"
        + "course cost is: %s", getCourseName(element), getCourseCost(element)));
    element.click();
    return new PreparationCoursesPage(guiceScoped);
  }

  public PreparationCoursesPage findMostExpensiveCourse() {
    WebElement element = coursesList.stream()
        .reduce((x1, x2) -> getCourseCost(x1) > getCourseCost(x2) ? x1 : x2)
        .orElse(null);
    LOGGER.info(String.format("We define the most expensive course\n"
        + "course name is: %s\n"
        + "course cost is: %s", getCourseName(element), getCourseCost(element)));
    element.click();
    return new PreparationCoursesPage(guiceScoped);
  }

  public Integer getCourseCost(WebElement element) {
    return Integer.parseInt(
      element.findElement(By.className("lessons__new-item-bottom")).getText().replaceAll("\\s₽", ""));
  }

  public String getCourseName(WebElement element) {
    return element.findElement(By.className("lessons__new-item-title_with-tags")).getText().replaceAll("\\s₽", "");
  }
}
