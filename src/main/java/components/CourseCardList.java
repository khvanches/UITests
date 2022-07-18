package components;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.DateParsing.getDateFromString;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.CourseDetailsPage;
import utils.GuiceScoped;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CourseCardList extends BasicComponentAbs<CourseCardList> {
  private WebElement actualCourse;
  @Inject
  public CourseCardList(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  @FindBy(css = ".lessons__new-item")
  private List<WebElement> courses;

  public List<WebElement> filterByName(String filter){
    List<WebElement> result = courses.stream()
        .filter(x -> getCourseName(x).contains(filter))
        .collect(Collectors.toList());
    if (!result.isEmpty()){
      return result;
    } else {
      try {
        throw new Exception(String.format("There are no any course that contains %s in the name", filter));
      } catch (Exception ex) {
        ex.printStackTrace();
        return null;
      }
    }
  }

  public CourseCardList filterByNameGetOne(String filter){
    actualCourse = filterByName(filter).stream()
        .findFirst()
        .orElse(null);
    return this;
  }

  public CourseCardList getEarliest(){
    actualCourse = courses.stream()
      .filter(x1 -> getDate(x1) != null)
      .reduce((x1,x2) -> getDate(x1).before(getDate(x2)) ? x1 : x2)
      .orElse(null);
    return this;
  }

  public CourseCardList getOldest(){
    actualCourse = courses.stream()
      .filter(x1 -> getDate(x1) != null)
      .reduce((x1,x2) -> getDate(x1).after(getDate(x2)) ? x1 : x2)
      .orElse(null);
    return this;
  }

  public CourseCardList getCourseStartAfterDate(Date date){
    actualCourse = courses.stream()
      .filter(x1 -> getDate(x1) != null)
      .filter(x1 -> getDate(x1).after(date))
      .findAny()
      .orElse(null);
    LOGGER.info(String.format("We have define the course that will start after/in expected date\n"
        + "Course name: %s, \n"
        + "Start date is: %s", getCourseName(actualCourse), getDate(actualCourse)));
    return this;
  }

  public CourseDetailsPage clickOnCard(){

    if (actualCourse != null) {
      scrollToElement.apply(guiceScoped.driver, actualCourse);
      actualCourse.click();
      return new CourseDetailsPage(guiceScoped);
    } else {
      try {
        throw new Exception("Actual course wasn't chosen! It's possible to click on all courses!");
      } catch (Exception ex) {
        ex.printStackTrace();
        return null;
      }
    }
  }

  private String getCourseName(WebElement webElement) {
    return webElement.findElement(By.className("lessons__new-item-title")).getText();
  }


  private Date getDate(WebElement webElement){
    WebElement lessonStart;
    WebElement lessonTime;

    if (isElementPresentedInElement(webElement, By.className("lessons__new-item-start"))) {
      lessonStart = webElement.findElement(By.className("lessons__new-item-start"));
    } else {
      lessonStart = null;
    }

    if (isElementPresentedInElement(webElement, By.className("lessons__new-item-time"))) {
      lessonTime = webElement.findElement(By.className("lessons__new-item-time"));
    } else {
      lessonTime = null;
    }

    if (lessonStart != null) {
      return getDateFromString(lessonStart.getText().substring(2));
    } else {
      if (lessonTime != null) {
        String str = lessonTime.getText();
        if (str.matches("^\\d.*")) {
          return getDateFromString(str);
        } else {
          return null;
        }
      } else {
        return null;
      }
    }
  }
}
