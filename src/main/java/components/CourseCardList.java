package components;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.DateParsing.getDateFromString;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.CourseDetailsPage;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CourseCardList extends BasicComponentAbs<CourseCardList> {
  private WebElement actualCourse;
  private Actions actions;

  public CourseCardList(WebDriver driver) {
    super(driver);
    actions = new Actions(driver);
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

  public WebElement filterByNameGetOne(String filter){
    return filterByName(filter).stream()
        .findFirst()
        .orElse(null);
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

  public CourseDetailsPage clickOnCard(){

    if (actualCourse != null) {
      String courseName = getCourseName(actualCourse).replace("Специализация ", "");
      actualCourse.click();

      assertThat(isExpectedCoursePageOpen(courseName))
        .as(String.format("Wrong course page has been opened! Course name on card: %s   Course name on page: %s",
          courseName, getOpenedCourseName()))
        .isTrue();
      return new CourseDetailsPage(driver);
    } else {
      try {
        throw new Exception("Actual course wasn't chosen! It's possible to click on all courses!");
      } catch (Exception ex) {
        ex.printStackTrace();
        return null;
      }
    }
  }

  public CourseDetailsPage actionsClickOnCard(){
    if (actualCourse != null) {
      String courseName = getCourseName(actualCourse).replace("Специализация ", "");
      scrollToElement.apply(driver, actualCourse);
      actions.moveToElement(actualCourse)
        .pause(1000)
        .build()
        .perform();
      actualCourse.click();
      assertThat(isExpectedCoursePageOpen(courseName))
        .as(String.format("Wrong course page has been opened! Course name on card: %s   Course name on page: %s",
          courseName, getOpenedCourseName()))
        .isTrue();
      return new CourseDetailsPage(driver);
    } else {
      try {
        throw new Exception("Actual course wasn't chosen! It's possible to click on all courses!");
      } catch (Exception ex) {
        ex.printStackTrace();
        return null;
      }
    }
  }

  private Boolean isExpectedCoursePageOpen(String courseName) {
    return getOpenedCourseName().contains(courseName);
  }

  private String getCourseName(WebElement webElement) {
    return webElement.findElement(By.className("lessons__new-item-title")).getText();
  }

  private String getOpenedCourseName(){
    if(waiter.elementIsPresented(By.cssSelector("[field='tn_text_1613574457579']"))) {
      return driver.findElement(By.cssSelector("[field='tn_text_1613574457579']")).getText();
    } else if(waiter.elementIsPresented(By.className("course-header2__title"))) {
      return driver.findElement(By.className("course-header2__title")).getText();
    } else {
      try {
        throw new Exception("Course name hasn't been found on a page! Please, recheck page and elements");
      } catch (Exception ex) {
        ex.printStackTrace();
        return null;
      }
    }
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
        if (str.matches(".*\\d.*")) {
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
